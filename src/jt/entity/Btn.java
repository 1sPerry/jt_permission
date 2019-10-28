package jt.entity;

/**
 * - 按钮实体类
 *
 * @author wangxiaopan
 * @date 2019/10/28 15:12
 */
public class Btn {
    private int id;
    private String btnName; //按钮名称
    private String stat;  //true 显示     false 隐藏
    private String des; //按钮描述
    private int d_flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getD_flag() {
        return d_flag;
    }

    public void setD_flag(int d_flag) {
        this.d_flag = d_flag;
    }
}
