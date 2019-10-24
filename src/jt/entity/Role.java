package jt.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * - 角色
 *
 * @author wangxiaopan
 * @date 2019/10/23 9:32
 */
public class Role {
    private int id;  //角色ID
    private String roleName;  //角色名称
    private List<Auth> authList = new ArrayList<Auth>();  //获取角色的所有权限

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Auth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }
}
