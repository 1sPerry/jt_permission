package jt.dao.impl;

import jt.dao.ModuleDao;
import jt.entity.Module;
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
 * @date 2019/10/23 10:19
 */
public class ModuleDaoImpl implements ModuleDao {
    @Override
    public Module findByID(int moduleId) {
        return null;
    }

    @Override
    public List<Module> listModules() {
        Connection conn = DBUtil.getConnection();
        List<Module> list = new ArrayList<Module>();
        String sql = "select * from sys_module order by id desc";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        int row = 0;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Module module = new Module();
                module.setId(resultSet.getInt("id"));
                module.setModuleName(resultSet.getString("moduleName"));
                list.add(module);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
