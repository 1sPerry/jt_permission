<%--
  Created by IntelliJ IDEA.
  User: wangxiaopan
  Date: 2019/10/22
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>用户登录页面</title>
</head>
<body >
<h2 align="center"><font color=red>用户登录页面</font></h2>
<span><font color="red">${msg}</font>
<form action="/jt/loginServlet?action=login" method="post">
  <table align="center" border="1">
    <tr>
      <td>用户名:</td>
      <td><input type="text" name="empName"></td>
    </tr>
    <tr>
      <td>密&nbsp;&nbsp;码:</td>
      <td><input type="text" name="password"></td>
    </tr>
    <tr>
      <td><input type="submit" value="登录" name="login"></td>
      <td><input type="reset" value="重置" name="reset"></td>
    </tr>
  </table>
  <p align="center"><a href="/register.jsp" color=blue>注册用户</a></p>
</form>

</body>
</html>

