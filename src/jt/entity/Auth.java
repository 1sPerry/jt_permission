package jt.entity;

/**
 * - 权限
 *
 * @author wangxiaopan
 * @date 2019/10/23 9:30
 */
public class Auth {
    private int id;
    private String authName; // 权限名称
    private String url; // 资源Url
    private String actionName; // 操作名称
    private int moduleId; // 模块ID
    private Module module; // 模块对象

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
