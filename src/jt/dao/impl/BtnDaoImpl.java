package jt.dao.impl;

import jt.dao.BtnDao;
import jt.entity.Auth;
import jt.entity.Btn;
import jt.util.DBUtil;
import jt.util.JtUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * - 按钮Dao
 *
 * @author wangxiaopan
 * @date 2019/10/28 15:15
 */
public class BtnDaoImpl implements BtnDao {


    @Override
    public List<Btn> selectBtns() {
        Connection conn = DBUtil.getConnection();
        List<Btn> btnList = new ArrayList<>();
        String sql = "SELECT  * FROM sys_btn  where  d_flag=1";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Btn btn = new Btn();
                btn.setId(resultSet.getInt(1));
                btn.setBtnName(resultSet.getString(2));
                btn.setStat(resultSet.getString(3));
                btn.setDes(resultSet.getString(4));
                btnList.add(btn);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pst, resultSet);
        }
        return btnList;
    }

    @Override
    public List<Btn> selectBtnByRoleId(int roleId) {
        Connection conn = DBUtil.getConnection();
        List<Btn> btns = new ArrayList<>();
        List<Btn> btnList = new ArrayList<>();
        String sql = "SELECT  sb.id,sb.btnName,sb.stat,des" +
                " FROM sys_btn sb" +
                " LEFT JOIN sys_btn_role  sbr ON sbr.btnId = sb.id" +
                " WHERE sbr.roleId IN( SELECT roleId FROM  sys_user_role  WHERE roleId='" + roleId + "' ) AND sb.d_flag =1  ";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Btn btn = new Btn();
                btn.setId(resultSet.getInt(1));
                btn.setBtnName(resultSet.getString(2));
                btn.setStat(resultSet.getString(3));
                btn.setDes(resultSet.getString(4));
                btns.add(btn);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pst, resultSet);
        }
        btnList = btns.stream()
                .filter(JtUtils.distinctByKey(Btn::getId))
                .collect(Collectors.toList());

        return btnList;
    }

    @Override
    public int saveRoleAuth(int roleId, int btnId) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into sys_btn_role (roleId,btnId) values (" + roleId + "," + btnId + ")";
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
    public int deleteByRole(int roleId) {
        Connection conn = DBUtil.getConnection();
        String sql = "delete from sys_btn_role  where roleId=" + roleId;
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
}
