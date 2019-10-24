package jt.dao.impl;

import jt.dao.AuthDao;
import jt.entity.Auth;
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
 * @date 2019/10/23 10:20
 */
public class AuthDaoImpl implements AuthDao {
    @Override
    public int delAuth(int authId) {
        Connection conn = DBUtil.getConnection();
        String sql = "update sys_auth SET d_flag = 0 where id=" + authId;
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
    public List<Auth> listAuths() {
        Connection conn = DBUtil.getConnection();
        List<Auth> auths = new ArrayList<>();
        String sql = "select  * from sys_auth where d_flag=1";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Auth auth = new Auth();
                auth.setId(resultSet.getInt(1));
                auth.setAuthName(resultSet.getString(2));
                auth.setUrl(resultSet.getString(3));
                auth.setActionName(resultSet.getString(4));
                auths.add(auth);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auths;
    }

    @Override
    public int saveAuth(Auth auth) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into sys_auth (authName,url,actionName) values ('" + auth.getAuthName() + "'," + auth.getUrl() + "," + auth.getActionName() + ")";
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
}
