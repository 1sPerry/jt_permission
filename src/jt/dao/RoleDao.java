package jt.dao;

import jt.entity.Auth;
import jt.entity.Role;

import java.util.List;

public interface RoleDao {
    // --------查找单个角色------------------------
    public Role findRoleByID(int roleId);
    // --------删除角色------------------------
    public boolean delRole(int roleId);
    // --------获取所有角色------------------------
    public List<Role> listRoles();
    // --------增加角色------------------------
    public boolean saveRole(Role role);
    // --------给角色指定权限-------------------
    public void assignAuthForRole(Role role);
    // --------获取角色没有指定的权限-----------
    public List<Auth> notAssignedAuths(int roleId);
    // --------获取角色已经指定的权限-----------
    public List<Auth> listAssigedAuths(int roleId);
}
