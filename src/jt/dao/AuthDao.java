package jt.dao;

import jt.entity.Auth;

import java.util.List;

public interface AuthDao {
    //---------删除权限----------------
     void delAuth(int authId);
    //---------获取所有权限------------
     List<Auth> listAuths();
    //---------添加单个权限------------
     void saveAuth(Auth auth);
}
