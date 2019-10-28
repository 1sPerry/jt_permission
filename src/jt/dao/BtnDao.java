package jt.dao;

import jt.entity.Btn;

import java.util.List;

public interface BtnDao {
    List<Btn> selectBtns();

    List<Btn> selectBtnByRoleId(int roleId);

   int saveRoleAuth(int roleId, int btnId);

    int deleteByRole(int roleId);
}
