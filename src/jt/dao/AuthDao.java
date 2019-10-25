package jt.dao;

import jt.entity.Auth;

import java.util.List;

public interface AuthDao {
    //---------删除权限----------------
     int delAuth(int authId);
    //---------获取所有权限------------
     List<Auth> listAuths();
    //---------添加单个权限------------
     int saveAuth(Auth auth);
    /**
     * 根据roleId获取所有权限
     */
    List<Auth> findAuthListByRoleId(int roleId);

    /**
     * 保存 角色权限关联表
     * @param roleId
     * @param authId
     * @return
     */
    int saveRoleAuth(int roleId,int authId);

    /**
     * 根据RoleId 删除权限
     * @param roleId
     * @return
     */
    int delAuthByRoleId(int roleId);
}
