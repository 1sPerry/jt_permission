package jt.dao.impl;

import jt.dao.RoleDao;
import jt.entity.Auth;
import jt.entity.Role;
import jt.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * -
 *
 * @author wangxiaopan
 * @date 2019/10/23 10:17
 */
public class RoleDaoImpl implements RoleDao {
    @Override
    public Role findRoleByID(int roleId) {
        return null;
    }

    @Override
    public int delRole(int roleId) {
        Connection conn = DBUtil.getConnection();
        String sql = "update sys_role SET d_flag = 0 where id=" + roleId;
        PreparedStatement pst = null;
        int rows = 0;
        try {
            pst = conn.prepareStatement(sql);
            rows = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pst);
        }
        return rows;
    }

    @Override
    public List<Role> listRoles() {
        Connection conn = DBUtil.getConnection();
        List<Role> roles = new ArrayList<>();
        String sql = "select  * from sys_role where d_flag=1";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setRoleName(resultSet.getString(2));
                roles.add(role);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pst,resultSet);
        }
        return roles;
    }

    @Override
    public int saveRole(Role role) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into sys_role (roleName) values ('" + role.getRoleName() + "')";
        PreparedStatement pst = null;
        int rows = 0;
        try {
            pst = conn.prepareStatement(sql);
            rows = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pst);
        }
        return rows;
    }

    @Override
    public void assignAuthForRole(Role role) {

    }

    @Override
    public List<Auth> notAssignedAuths(int roleId) {
        return null;
    }

    @Override
    public List<Auth> listAssigedAuths(int roleId) {
        return null;
    }
}
