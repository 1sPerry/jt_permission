package jt.service;

import jt.entity.User;

public interface UserService {
    //注册
    int register(User user);

    // 登录
    User loginUser(String username, String password);
}
