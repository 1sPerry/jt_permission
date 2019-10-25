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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String action = request.getParameter("action");
        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
        }
    }

    //----------进行登录的操作--------------------------------------
    public void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String empName = req.getParameter("empName");
        String password = req.getParameter("password");
        UserDaoImpl userDao = new UserDaoImpl();
        User userInfo = userDao.loginUser(empName,password);
        boolean isLogin =false;
        List<User> list=userDao.listUsers();
            for (int i = 0; i < list.size(); i++) {
            User u=(User)list.get(i);
            if(u.getEmpName().equals(userInfo.getEmpName())&& u.getPassword().equals(userInfo.getPassword())){
                isLogin =true;
            }
        }
        if (isLogin) {// 如果不为空，说明登录成功
            HttpSession session = req.getSession();
            session.setAttribute("user", userInfo);
            req.getRequestDispatcher(SUCCESS).forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误,请重新登录！");
            req.getRequestDispatcher(LOGIN).forward(req, resp);
        }
    }
    //---------注销模块，退出后转到login.jsp--------------------------------
    public void logout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        req.getRequestDispatcher(LOGIN).forward(req, resp);
    }
}
