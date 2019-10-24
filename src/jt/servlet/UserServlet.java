package jt.servlet;

import jt.dao.impl.RoleDaoImpl;
import jt.dao.impl.UserDaoImpl;
import jt.entity.Role;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jt/userServlet")
public class UserServlet extends HttpServlet {
    private String empName;
    private static String ASSIGNROLE="/assignRole.jsp";
    private static String LOGIN="/login.jsp";
    private static String USER_LIST="/userList.jsp";
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
        } else if (action.equals("assign")) {
//            assign(request, response);
        }
    }

    /**
     * 从数据库中查询所有的用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void userList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserDaoImpl userDao=new UserDaoImpl();
        List<User> userList =userDao.listUsers();
        req.setAttribute("userList", userList);
        req.getRequestDispatcher(USER_LIST).forward(req,resp);
    }

    /**
     * 注销用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void del(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        UserDaoImpl userDao=new UserDaoImpl();
       int rows = userDao.delUser(userId);
      //注销用户后，请求用户列表接口
        userList(req,resp);
    }
    /**
     * 添加用户
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
        UserDaoImpl userDao=new UserDaoImpl();
       int rows= userDao.saveUser(user);
        //注销用户后，请求用户列表接口
        userList(req,resp);
    }
    /**
     * 给用户分配角色
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void assignRole(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        String empName = req.getParameter("empName");
        UserDaoImpl userDao=new UserDaoImpl();
        Role role = userDao.selectRoleById(userId);
        //注销用户后，请求用户列表接口
        req.setAttribute("empName", empName);
        req.setAttribute("roleInfo", role);
        List<Role> roles = new ArrayList<>();
        RoleDaoImpl roleDao = new RoleDaoImpl();
        roles = roleDao.listRoles();
        req.setAttribute("roles", roles);//这里遍历list 跟上面 roleInfo比  就下拉框 遍历 然后选中 zhe ji  ge
        req.getRequestDispatcher(ASSIGNROLE).forward(req, resp);
    }
}
