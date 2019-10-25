package jt.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * - 数据库连接池
 *
 * @author wangxiaopan
 * @date 2019/10/23 17:35
 */
public class DBUtil {
    // 加载数据库驱动  com.mysql.jdbc.Driver
    private static String driverClass;
    // 获取mysql连接地址，此处user为数据库名称
    private static String url;
    // 数据用户名
    private static String username;
    // 数据库密码
    private static String password;
    // 获取一个数据的连接
    private static Connection conn = null;

    // 静态代码块
    public static Connection getConnection() {
        try {
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("properties/jdbccfg.properties");
            Properties props = new Properties();
            props.load(in);
            driverClass = props.getProperty("driverClass");
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
            in.close();
        } catch (IOException e) {
            // 转换异常抛出
            throw new ExceptionInInitializerError("获取数据库配置文件信息失败");
        }
        try {
            Class.forName(driverClass);
            //getConnection()方法，连接MySQL数据库！
            conn = DriverManager.getConnection(url, username, password);
            if (!conn.isClosed())
                System.out.println("数据库连接成功！");
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        } catch (SQLException e1) {
            //数据库连接失败异常处理
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            System.out.println("-------------------------------");
            System.out.println("数据库数据获取成功！");
        }
        return conn;
    }

    /**
     * 若连接由连接池创建，则关闭连接
     * 就是将其归还给连接池，该连接的
     * 状态会变成空闲，可以继续复用。
     */
    public static void close(
            Connection con, Statement smt) {
        try {
            if (smt != null) {
                smt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("释放资源失败", e);
        }
    }

    public static void close(Connection con, Statement smt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (smt != null) {
                smt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("释放资源失败", e);
        }
    }

    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();
        //System.out.println(con.getClass());
        DBUtil.close(con, null);
    }
}

