package jt.filter;

import jt.cache.MyCacheManager;
import jt.dao.impl.RoleDaoImpl;
import jt.dao.impl.UserDaoImpl;
import jt.entity.Auth;
import jt.entity.Role;
import jt.entity.User;

import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * - Filter 过滤器
 *
 * @author wangxiaopan
 * @date 2019/10/23 10:07
 */
@WebFilter(value = {"/jt/*"})
public class SecureFilter implements Filter {
    private static List<String> baseSourceContainer = new ArrayList<>();
    private static List<String> fileContainer = new ArrayList<>();
    private static List<String> actionContainer = new ArrayList<>();
    private FilterConfig config = null;
    private ServletContext ctx = null;
    private static final String LOGIN = "/login.jsp";
    private static final String NOAUTHORITY = "/noAuthority.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        //初始化放行列表,后缀和路径放在两个不同的容器里面
        initSecureContainer();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI(); // 获取请求路径
        String action = req.getParameter("action");
        // 如果回话中的用户为空,页面重新定向到登陆页面
        if (!isLogined(req)) { // 如果是没有登录的用户，则执行以下的代码
            // 请求登录页面login.jsp或提交登录请求
            if ("regist".equals(action)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                if (validateSuffix(path) || validatePage(path)
                        || validateAction(action)) {
                    filterChain.doFilter(req, resp);
                } else {
                    ctx = config.getServletContext();
                    // 如果用sendRedirect还会进行过滤，所以不用
                    ctx.getRequestDispatcher(LOGIN).forward(req, resp);
                    return;// 到这里时候已经出错了，不用再往下传递请求了。
                }
            }
        } else {// 如果用户已经登录，在判断他是否有访问某一资源的权限
            try {
                // 如果用户没有当前页的权限,页面重新定向到上一页面
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("user");// 是在Servlet中放入的User对象，必须从session中获取user对象
                //如果是login请求，则不进行过滤
                if ("login".equals(action)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    // 进行权限检查
                    if (checkSafe(req, user)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        // 权限不够界面
                        String message = "您没有该权限，请联系管理员！";
                        message += "<a href='javascript:window.history.go(-1)'>返回</a>";
                        req.setAttribute("msg", message);
                        ctx = config.getServletContext();
                        ctx.getRequestDispatcher(NOAUTHORITY).forward(req, resp);
                        return;
                    }
                }
            } catch (Throwable e) {
                throw new RuntimeException("权限过滤时候出现错误", e);
            }// end try
        }// end else
    }

    @Override
    public void destroy() {
        System.out.printf("-----destroy--------");
    }

    /**
     * 判断是否是已经登陆的用户
     *
     * @param request
     * @return
     */
    private boolean isLogined(HttpServletRequest request) {
        boolean flag = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {// 保存的是一个User对象
            flag = true;
        }
        return flag;
    }

    /**
     * 对请求页进行检查
     *
     * @param path
     * @return
     */
    private boolean validatePage(String path) {
        int pos = path.lastIndexOf("/");
        String resource = path.substring(pos + 1);
        // 登陆之前放行的基本元素,因为这些元素都是登录页面显示时所必须要的。
        if (fileContainer.contains(resource)) {
            return true;
        }
        return false;
    }

    /**
     * 对请求资源后缀进行检查
     *
     * @param path
     * @return
     */
    private boolean validateSuffix(String path) {
        String suffix = "";
        int pos2 = path.lastIndexOf(".");// 当请求的路径中不存在"."时值为-1
        if (pos2 != -1) {
            suffix = path.substring(pos2);
        }
        if (baseSourceContainer.contains(suffix)) {
            return true;
        }
        return false;
    }

    /**
     * 对请求资源参数进行检查
     *
     * @param action
     * @return
     */
    private boolean validateAction(String action) {
        if (actionContainer.contains(action)) {
            return true;
        }
        return false;
    }

    /**
     * 初始化放行列表,后缀和路径放在两个不同的容器里面
     */
    private void initSecureContainer() {
        // 放的是基本资源的后缀
        baseSourceContainer.add(".css");
        baseSourceContainer.add(".js");
        baseSourceContainer.add(".jpg");
        baseSourceContainer.add(".png");
        fileContainer.add("login.jsp");
        actionContainer.add("login");
    }

    /**
     * 判断某个员工是否有操作的权限
     *
     * @param request
     * @param user
     * @return
     */
    private boolean checkSafe(HttpServletRequest request, User user) {
        boolean index = false;
        String url = request.getRequestURI();
        String action = request.getParameter("action");
        //查询数据库当前用户所拥有的权限是否包含此 action
        UserDaoImpl userDao = new UserDaoImpl();
        List<Auth> auths = userDao.findAllAuthByUserId(user.getId());
        if (null != auths && auths.size() > 0) {
            //若当前权限包含此action，则放行，否则跳转到无权限页面
            for (Auth auth : auths) {
                String urlInfo = auth.getUrl();
                String actionInfo = auth.getActionName();
                if (action.equals(actionInfo) && url.equals(urlInfo)) {
                    index = true;
                }
            }
        }
        return index;
    }

}
