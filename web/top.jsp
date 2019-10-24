<%--
  Created by IntelliJ IDEA.
  User: wangxiaopan
  Date: 2019/10/22
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Top</title>
</head>
<body >
<h2 align="center"><font color=red>Top 首页</font></h2>
<div id="top">
    <span>欢迎:<font color="red">${sessionScope.user.empName}</font> 登陸!
        <tr>
            <a align="center" href="/jt/userServlet?action=userList"> 用户管理 </a>
             <a align="center" href="/jt/userServlet?action=roleList"> 角色管理 </a>
             <a align="center" href="/jt/userServlet?action=authList"> 权限管理 </a>
         </tr>

<%--        --%>
<%-- <a href="/jt/loginServlet?action=list">--%>
<%--   <img align="absmiddle"/>员工管理--%>
<%-- </a>--%>
</span></div>
</body>
</html>

