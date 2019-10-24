package jt.dao;

import jt.entity.Auth;
import jt.entity.Role;
import jt.entity.User;

import java.util.List;

public interface UserDao {
    //注册
    int register(User user);
    // 登录
    User loginUser(String username, String password);
    //--------删除员工------------------------
     int delUser(int userId);
    //--------返回所有的员工------------------
     List<User> listUsers();
    //--------保存员工------------------------
     int saveUser(User user);
    //--------获取单个员工--------------------
     User findByID(int userId);
    //--------获取员工已经指定的角色-----------
     List<Role> findAllAssignedRoles(int userId);
    //--------获取员工没有指定的角色-------------
     List<Role> findNotAssignedRoles(int userId);
    //--------给员工指定角色-------------------
     void assignRoleForEmp(User user);
     //根据userId查询当前用户所有的权限并去重
    List<Auth> findAllAuthByUserId(int userId);
    //根据ID 查询用户的角色
    Role selectRoleById(int id);


}
