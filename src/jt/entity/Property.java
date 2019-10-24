package jt.entity;

/**
 * - URL / ACTION
 *
 * @author wangxiaopan
 * @date 2019/10/23 9:33
 */
public class Property {
    //-------声明私有属性--------------------------
    private String url;
    private String action;

    //-------构造方法初始化私有属性------------------------------
    public Property(String url, String action) {
        this.url = url;
        this.action = action;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
