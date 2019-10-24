package jt.service.impl;

import jt.dao.impl.UserDaoImpl;
import jt.entity.User;
import jt.service.UserService;

/**
 * - UserServiceImpl
 *
 * @author wangxiaopan
 * @date 2019/10/22 14:26
 */
public class UserServiceImpl implements UserService {
    private UserDaoImpl userDaoImpl;

    public UserDaoImpl getUserDaoImpl() {
        return userDaoImpl;
    }

    public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Override
    public int register(User user) {
        int row = 0;
        row = userDaoImpl.register(user);
        return row;
    }

    @Override
    public User loginUser(String username, String password) {
        return userDaoImpl.loginUser(username,password);
    }

    /**
     * 查找是否是已注册用户
     * @param username
     * @return
     */
    public int findIsLoginUser(String username) {
        int row = 0;
        row = userDaoImpl.findIsLoginUser(username);
        return row;
    }
}