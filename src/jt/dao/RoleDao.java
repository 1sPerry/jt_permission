package jt.dao;

import jt.entity.Auth;
import jt.entity.Role;

import java.util.List;

public interface RoleDao {
    /**
     * 查找单个角色
     *
     * @param roleId
     * @return
     */
    Role findRoleByID(int roleId);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    int delRole(int roleId);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Role> listRoles();

    /**
     * 增加角色
     *
     * @param role
     * @return
     */
    int saveRole(Role role);

    /**
     * 给角色指定权限
     *
     * @param role
     */
    void assignAuthForRole(Role role);

    /**
     * 获取角色没有指定的权限
     *
     * @param roleId
     * @return
     */
    List<Auth> notAssignedAuths(int roleId);

    /**
     * 获取角色已经指定的权限
     *
     * @param roleId
     * @return
     */
    List<Auth> listAssigedAuths(int roleId);
}
