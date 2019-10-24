package jt.cache;

import jt.entity.Role;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * - 缓存配置
 *
 * @author wangxiaopan
 * @date 2019/10/23 9:35
 */
public class MyCacheManager {
    private CacheManager manager;          // 缓存管理器
    private Cache cache;                   // 缓存

    // -------初始化缓存--------------------------------------
    public void initialize() {
        manager = CacheManager.create();
        cache = manager.getCache("mycache");// 获取一个Cache对象
    }

    // -------向缓存中存入内容--------------------------------
    public void put(int key, Role value) {  // 将对象加入缓存
        Element element = new Element(key, value);
        cache.put(element);
    }

    // -------根据Role.getId()取得特定的Role对象---------------
    public Role get(int key) {
        Element value = cache.get(key);      // 根据key取出值
        Role role = (Role) value.getObjectValue();// 从缓存中取出 一个Role对象
        return role;
    }
}
