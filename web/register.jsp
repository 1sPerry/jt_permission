<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body align="center">
<h2>新用户注册</h2>
<form action="/jt/registServlet?action=regist" method="post">
  <table align="center">
    <tr align="right">
      <td>请输入用户名:</td>
      <td><input type="text" name="username" autofocus="autofocus"></td>
    </tr>
    <tr align="right">
      <td>请输入密码:</td>
      <td><input type="password" name="password"></td>
    </tr>
    <tr align="right">
      <td>请输入确认密码:</td>
      <td><input type="password" name="repeatPsd"></td>
    </tr>
  </table>
  <input type="submit" name="register" value="注册" >
  <input type="reset" name="reset" value="重置" >
</form>


</body>
</html>
