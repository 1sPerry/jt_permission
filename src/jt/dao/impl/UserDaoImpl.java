package jt.dao.impl;

import jt.dao.UserDao;
import jt.entity.Auth;
import jt.entity.Role;
import jt.entity.User;
import jt.util.DBUtil;
import jt.util.JtUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * - UserDaoImpl
 *
 * @author wangxiaopan
 * @date 2019/10/22 14:25
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int register(User user) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into sys_user(userId,branchId,nickname,avatar,username,password,email,phone) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        int row = 0;
        try {
            pst = conn.prepareStatement(sql);
            row = pst.executeUpdate();
            System.out.println("row = " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public User loginUser(String username, String password) {
        User user = new User();
        Connection conn = DBUtil.getConnection();
        String sql = "select * from sys_user where username='+username +' and password='+password +' where d_flag=1";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int findIsLoginUser(String username) {
        Connection conn = DBUtil.getConnection();
        String sql = "select count(*) from sys_user where username='+username +'";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        int row = 0;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                row = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int delUser(int userId) {
        Connection conn = DBUtil.getConnection();
        String sql = "update sys_user SET d_flag = 0 where id=" + userId;
        PreparedStatement pst = null;
        int rows = 0;
        try {
            pst = conn.prepareStatement(sql);
            rows = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<User> listUsers() {
        Connection conn = DBUtil.getConnection();
        List<User> list = new ArrayList<User>();
        String sql = " SELECT" +
                " u.id," +
                " u.empName," +
                " u.password," +
                " roleId," +
                " roleName " +
                " FROM sys_user u" +
                " LEFT JOIN (SELECT r.id AS roleId, ur.userId, r.roleName  FROM sys_role r INNER JOIN sys_user_role ur ON r.id = ur.roleId  ) role ON role.userId = u.id " +
                " WHERE u.d_flag=1";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        int row = 0;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmpName(resultSet.getString("empName"));
                user.setPassword(resultSet.getString("password"));
                user.setPosition(resultSet.getString("roleName"));
                list.add(user);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int saveUser(User user) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into sys_user (empName,password) values ('" + user.getEmpName() + "','" + user.getPassword() + "')";
        PreparedStatement pst = null;
        int rows = 0;
        try {
            pst = conn.prepareStatement(sql);
            rows = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public User findByID(int userId) {
        return null;
    }

    @Override
    public List<Role> findAllAssignedRoles(int userId) {
        return null;
    }

    @Override
    public List<Role> findNotAssignedRoles(int userId) {
        return null;
    }

    @Override
    public int addAssignRoleForUser(User user) {
        Connection conn = DBUtil.getConnection();
        String sql ="insert into sys_user_role (userId,roleId) values ('" + user.getId() + "','" + user.getRoleId() + "')";
        PreparedStatement pst = null;
        int rows = 0;
        try {
            pst = conn.prepareStatement(sql);
            rows = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
    @Override
    public int updateAssignRoleForUser(User user) {
        Connection conn = DBUtil.getConnection();
    String sql= "update sys_user_role  set roleId=" + user.getRoleId() + " where userId=" + user.getId() ;
        PreparedStatement pst = null;
        int rows = 0;
        try {
            pst = conn.prepareStatement(sql);
            rows = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Auth> findAllAuthByUserId(int userId) {
        Connection conn = DBUtil.getConnection();
        List<Auth> authList = new ArrayList<Auth>();
        List<Auth> auths = new ArrayList<Auth>();
        String sql = " SELECT" +
                " authName," +
                " url," +
                " actionName, " +
                " authId " +
                " FROM" +
                " sys_user u" +
                " INNER JOIN ( SELECT r.id, userId, roleName FROM sys_role r INNER JOIN sys_user_role ur ON r.id = ur.roleId ) role ON role.userId = u.id" +
                " INNER JOIN ( SELECT a.id AS authId, roleId, authName, url,actionName FROM sys_auth a INNER JOIN sys_role_auth ra ON a.id = ra.authId ) auth ON auth.roleid = role.id " +
                " WHERE" +
                " u.id =" + userId;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Auth auth = new Auth();
                auth.setAuthName(resultSet.getString(1));
                auth.setUrl(resultSet.getString(2));
                auth.setActionName(resultSet.getString(3));
                auth.setId(resultSet.getInt(4));
                auths.add(auth);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        authList = auths.stream()
                .filter(JtUtils.distinctByKey(Auth::getId))
                .collect(Collectors.toList());

        return authList;
    }

    @Override
    public Role selectRoleById(int id) {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT userId ,roleName FROM sys_user_role ur" +
                " LEFT JOIN sys_role r ON r.id = ur.roleId " +
                " WHERE ur.userId =" + id;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Role role = new Role();
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                role.setId(resultSet.getInt(1));
                role.setRoleName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    @Override
    public int selectUserById(int id) {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM sys_user_role  WHERE userId =" + id;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        int rows=0;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            if (!resultSet.next()) {
                rows =1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

}
