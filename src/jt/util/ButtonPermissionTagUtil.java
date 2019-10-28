package jt.util;


import jt.dao.impl.UserDaoImpl;
import jt.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * - 自定义标签处理类
 *
 * @author wangxiaopan
 * @date 2019/10/28 10:54
 */
public class ButtonPermissionTagUtil extends TagSupport {

    /**
     *
     */
    private static final long serialVersionUID = 3995418178864580338L;

    private String name;
    public ButtonPermissionTagUtil() {
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            HttpSession session = pageContext.getSession();
            User u = (User) session.getAttribute("user");
            UserDaoImpl userDao = new UserDaoImpl();
            List<String> btnNameList = userDao.selectBtnName(u.getId());
            boolean is = false;
            for (String btnName : btnNameList) {
                if (name.equals(btnName)) {
                    is = true;
                }
            }
            if (is) {
                return EVAL_BODY_INCLUDE;
            }
            return SKIP_BODY;
        } catch (Exception e) {
            throw new RuntimeException("数据加载异常", e);
        }
    }
    public void setName(String name) {
        this.name = name;
    }

}