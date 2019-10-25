package jt.dao;

import jt.entity.Module;

import java.util.List;

public interface ModuleDao {

    /**
     * 获取单个模块
     *
     * @param moduleId
     * @return
     */
    Module findByID(int moduleId);

    /**
     * 获取所有模块
     *
     * @return
     */
    List<Module> listModules();
}
