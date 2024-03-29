package jt.servlet;

import jt.dao.impl.AuthDaoImpl;
import jt.dao.impl.BtnDaoImpl;
import jt.dao.impl.RoleDaoImpl;
import jt.dao.impl.UserDaoImpl;
import jt.entity.Auth;
import jt.entity.Btn;
import jt.entity.Role;
import jt.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jt/userServlet")
public class UserServlet extends HttpServlet {
    private static String LOGIN = "/login.jsp";
    private static String USER_LIST = "/userList.jsp";
    private static String ROLE_LIST = "/roleList.jsp";
    private static String ASSIGNROLE = "/assignRole.jsp";
    private static String ASSIGNAUTH = "/assignAuth.jsp";
    private static String AUTH_LIST = "/authList.jsp";
    private static String ASSIGN_BTN = "/assignBtn.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(req, resp);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("userList")) {
            userList(request, response);
        } else if (action.equals("del")) {
            del(request, response);
        } else if (action.equals("addUser")) {
            addUser(request, response);
        } else if (action.equals("assignRole")) {
            assignRole(request, response);
        } else if (action.equals("roleList")) {
            roleList(request, response);
        } else if (action.equals("delRole")) {
            delRole(request, response);
        } else if (action.equals("addRole")) {
            addRole(request, response);
        } else if (action.equals("assignAuth")) {
            assignAuth(request, response);
        } else if (action.equals("authList")) {
            authList(request, response);
        } else if (action.equals("addAuth")) {
            addAuth(request, response);
        } else if (action.equals("delAuth")) {
            delAuth(request, response);
        } else if (action.equals("assignRoleAdd")) {
            assignRoleAdd(request, response);
        } else if (action.equals("assignAuthAdd")) {
            assignAuthAdd(request, response);
        } else if (action.equals("assignBtn")) {
            assignBtn(request, response);
        } else if (action.equals("assignBtnAdd")) {
            assignBtnAdd(request, response);
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
    }

    /**
     * 从数据库中查询所有的用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void userList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> userList = userDao.listUsers();
        req.setAttribute("userList", userList);
        req.getRequestDispatcher(USER_LIST).forward(req, resp);
    }

    /**
     * 注销用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void del(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        UserDaoImpl userDao = new UserDaoImpl();
        int rows = userDao.delUser(userId);
        //注销用户后，请求用户列表接口
        userList(req, resp);
    }

    /**
     * 添加用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String empName = req.getParameter("empName");
        String password = req.getParameter("password");

        User user = new User();
        user.setEmpName(empName);
        user.setPassword(password);
        UserDaoImpl userDao = new UserDaoImpl();
        int rows = userDao.saveUser(user);
        //注销用户后，请求用户列表接口
        userList(req, resp);
    }

    /**
     * 给用户分配角色
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void assignRole(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        String empName = req.getParameter("empName");
        UserDaoImpl userDao = new UserDaoImpl();
        Role role = userDao.selectRoleById(userId);
        //注销用户后，请求用户列表接口
        req.setAttribute("userId", userId);
        req.setAttribute("empName", empName);
        req.setAttribute("roleNameInfo", role.getRoleName());
        List<Role> roles = new ArrayList<>();
        RoleDaoImpl roleDao = new RoleDaoImpl();
        roles = roleDao.listRoles();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher(ASSIGNROLE).forward(req, resp);
    }

    /**
     * 添加用户角色
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void assignRoleAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int roleId = Integer.parseInt(req.getParameter("sel"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = new User();
        user.setId(userId);
        user.setRoleId(roleId);
        /**
         * 先查询关联表中userID是否为空，若不为空 update 若为空，insert
         */
        UserDaoImpl userDao = new UserDaoImpl();
        int isNull = userDao.selectUserById(userId);
        if (0 == isNull) {
            userDao.updateAssignRoleForUser(user);
        } else {
            int rows = userDao.addAssignRoleForUser(user);
        }
        userList(req, resp);
    }

    /**
     * 从数据库中查询所有的角色
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void roleList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RoleDaoImpl roleDao = new RoleDaoImpl();
        List<Role> roleList = roleDao.listRoles();
        req.setAttribute("roleList", roleList);
        req.getRequestDispatcher(ROLE_LIST).forward(req, resp);
    }

    /**
     * 注销角色
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delRole(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer roleId = Integer.parseInt(req.getParameter("roleId"));
        RoleDaoImpl roleDao = new RoleDaoImpl();
        int rows = roleDao.delRole(roleId);
        //注销用户后，请求用户列表接口
        roleList(req, resp);
    }

    /**
     * 添加角色
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addRole(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//传值编码
        resp.setContentType("text/html;charset=UTF-8");//设置传输编码
        String roleName = req.getParameter("roleName");
        Role role = new Role();
        role.setRoleName(roleName);
        RoleDaoImpl roleDao = new RoleDaoImpl();
        int rows = roleDao.saveRole(role);
        //注销用户后，请求用户列表接口
        roleList(req, resp);
    }

    /**
     * 给用角色分配权限
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void assignAuth(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer roleId = Integer.parseInt(req.getParameter("roleId"));
        AuthDaoImpl authDao = new AuthDaoImpl();
        List<Auth> authList = authDao.findAuthListByRoleId(roleId);
        List<Auth> authListAll = authDao.listAuths();
        req.setAttribute("roleId", roleId);
        req.setAttribute("authList", authList);
        req.setAttribute("authListAll", authListAll);
        req.getRequestDispatcher(ASSIGNAUTH).forward(req, resp);
    }

    /**
     * 从数据库中查询所有的权限
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void authList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        AuthDaoImpl authDao = new AuthDaoImpl();
        List<Auth> authList = authDao.listAuths();
        req.setAttribute("authList", authList);
        req.getRequestDispatcher(AUTH_LIST).forward(req, resp);
    }

    /**
     * 添加权限
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addAuth(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String authName = req.getParameter("authName");
        String url = req.getParameter("url");
        String actionName = req.getParameter("actionName");
        Auth auth = new Auth();
        auth.setAuthName(authName);
        auth.setUrl(url);
        auth.setActionName(actionName);
        AuthDaoImpl authDao = new AuthDaoImpl();
        int rows = authDao.saveAuth(auth);
        authList(req, resp);
    }

    /**
     * 添加权限确认
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void assignAuthAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String[] authIds = req.getParameterValues("authIds");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        AuthDaoImpl authDao = new AuthDaoImpl();
        authDao.delAuthByRoleId(roleId);
        //先删除角色权限表中当前角色关联权限，再进行新增
        for (int i = 0; i < authIds.length; i++) {
            int authId = Integer.parseInt(authIds[i]);
            authDao.saveRoleAuth(roleId, authId);
        }
        roleList(req, resp);
    }

    /**
     * 注销权限
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delAuth(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer authId = Integer.parseInt(req.getParameter("authId"));
        AuthDaoImpl authDao = new AuthDaoImpl();
        int rows = authDao.delAuth(authId);
        authList(req, resp);
    }

    /**
     * 添加按钮
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void assignBtn(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        req.setAttribute("roleId", roleId);
        BtnDaoImpl btnDao = new BtnDaoImpl();
        List<Btn> btnList = btnDao.selectBtnByRoleId(roleId);
        req.setAttribute("btnList", btnList);
        List<Btn> btns = btnDao.selectBtns();
        req.setAttribute("btns", btns);
        req.getRequestDispatcher(ASSIGN_BTN).forward(req, resp);
        authList(req, resp);
    }

    /**
     * 添加按钮权限确认
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void assignBtnAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String[] btnIds = req.getParameterValues("btnIds");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        BtnDaoImpl btnDao = new BtnDaoImpl();
        btnDao.deleteByRole(roleId);
        //先删除角色权限表中当前角色关联权限，再进行新增
        for (int i = 0; i < btnIds.length; i++) {
            int btnId = Integer.parseInt(btnIds[i]);
            btnDao.saveRoleAuth(roleId, btnId);
        }
        roleList(req, resp);
    }
}
