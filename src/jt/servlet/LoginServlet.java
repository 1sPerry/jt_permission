package jt.servlet;

import jt.dao.impl.UserDaoImpl;
import jt.entity.User;
import jt.util.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet("/jt/loginServlet")
public class LoginServlet extends HttpServlet {
    private String empName;
    private static String SUCCESS="/top.jsp";
    private static String LOGIN="/login.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(req, resp);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("logout")) {
//            logout(request, response);
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ptst = null;
        User user = new User();
        String empName = request.getParameter("empName");
        String password = request.getParameter("password");
        Connection conn = null;
        try {
            conn = new DBUtil().getConnection();
                try {
                    String sql="select id,empName,password,position from sys_user where empName='"+empName+"' and  password='"+password+"'";
                    st = conn.createStatement();
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        user.setId(rs.getInt(1));
                        user.setEmpName(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setPosition(rs.getString(4));
                    }
                    rs.close();
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        out.flush();
        out.close();
        System.out.println("----------登陆成功--" + user.toString());
    }

    //----------进行登录的操作--------------------------------------
    public void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user=null;
        UserDaoImpl userDao = new UserDaoImpl();
        User userInfo = getUserFromReq(req);
        List<User> list=userDao.listUsers();;
            for (int i = 0; i < list.size(); i++) {
            User u=(User)list.get(i);
            if(u.getEmpName().equals(userInfo.getEmpName())&& u.getPassword().equals(userInfo.getPassword())){
                userInfo=u;
            }
        }
        if (userInfo!= null) {// 如果不为空，说明登录成功
            HttpSession session = req.getSession();
            //将Employee对象保存到session范围内
            session.setAttribute("user", userInfo);
            //添加模块到list  首页遍历
//            ModuleDaoImpl moduleDao = new ModuleDaoImpl();
//            List<Module> modules = new ArrayList<>();
//            modules = moduleDao.listModules();
//            req.setAttribute("moduleList", modules );
            req.getRequestDispatcher(SUCCESS).forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误");
            req.getRequestDispatcher(LOGIN).forward(req, resp);
        }
    }
    // ------------利用request对象生成 User--------------------------------
    private User getUserFromReq(HttpServletRequest request) {
        User user = new User();
        String id = request.getParameter("userId");
        if (id != null && !id.equals("")) {// 如果是登录的时候，则没有id号
            user.setId(Integer.parseInt(id));
        }
        String position = request.getParameter("position");
        if (position != null && !position.equals("")) {// 如果是登录的时候，则没有职位值
            user.setPosition(position);
        }
        user.setEmpName(request.getParameter("empName"));
        user.setPassword(request.getParameter("password"));
        return user;
    }
}
