package jt.dao;

import jt.entity.Auth;
import jt.entity.Role;
import jt.entity.User;

import java.util.List;

public interface UserDao {
    /**
     * 注册
     *
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    User loginUser(String username, String password);

    /**
     * 删除员工
     *
     * @param userId
     * @return
     */
    int delUser(int userId);

    /**
     * 返回所有的员工
     *
     * @return
     */
    List<User> listUsers();

    /**
     * 保存员工
     *
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 获取单个员工
     *
     * @param userId
     * @return
     */
    User findByID(int userId);

    /**
     * 获取员工已经指定的角色
     *
     * @param userId
     * @return
     */
    List<Role> findAllAssignedRoles(int userId);

    /**
     * 获取员工没有指定的角色
     *
     * @param userId
     * @return
     */
    List<Role> findNotAssignedRoles(int userId);

    /**
     * 给员工指定角色
     *
     * @param user
     * @return
     */
    int updateAssignRoleForUser(User user);

    int addAssignRoleForUser(User user);

    /**
     * 根据userId查询当前用户所有的权限并去重
     *
     * @param userId
     * @return
     */
    List<Auth> findAllAuthByUserId(int userId);

    /**
     * 根据ID 查询用户的角色
     *
     * @param id
     * @return
     */
    Role selectRoleById(int id);

    /**
     * 根据ID查询User
     *
     * @param id
     * @return
     */
    int selectUserById(int id);


}
