package jt.servlet;

import jt.util.DBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * - 注册
 *
 * @author wangxiaopan
 * @date 2019/10/22 14:41
 */

@WebServlet("/jt/registServlet")
public class RegistServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ptst = null;
        List<String> userList = new ArrayList<String>();
        String registName = request.getParameter("username");
        String registPassword = request.getParameter("password");
        String registRepeatpsd = request.getParameter("repeatPsd");
        Connection conn = null;
        try {
            conn = new DBUtil().getConnection();
            // 判断两次密码是否一致：
            if (registPassword.equals(registRepeatpsd)) {
                try {
                    /**
                     * 判断用户表中是否已经存在该用户
                     * 1.遍历sys_user表中所用的empName字段
                     * 2.将empName字段中的所有数据存入集合中；
                     * 3.判断集合中和否含有注册的用户名
                     *         3.1：如果有，返回到error页面
                     *         3.2：如果没有，进行注册操作
                     */
                    //user
                    String select = "select empName from sys_user";
                    st = conn.createStatement();
                    rs = st.executeQuery(select);
                    while (rs.next()) {
                        userList.add(rs.getString(1));
                    }
                    rs.close();
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (userList.contains(registName)) {
                    out.println("用户名已注册，请重新尝试。");
                } else {
                    String insert = "insert into sys_user(empName,password) values(?,?)";
                    try {
                        ptst = conn.prepareStatement(insert);
                        //设置ptst参数
                        ptst.setString(1, registName);
                        ptst.setString(2, registPassword);
                        ptst.execute();
                        out.println("注册成功！");
                        ptst.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                out.println("两次密码输入不一致，请重新尝试。");
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
    }
}