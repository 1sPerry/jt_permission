<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/top.css" type="text/css" rel="stylesheet">
    <link href="/main.css" type="text/css" rel="stylesheet">
    <title>添加用户</title>
</head>
<body>
<h2 align="center"><font color=red>添加用户</font></h2>
<form name="form2" method="post" action="/jt/userServlet?action=addUser" >
    <input type="hidden" name="action" value="save"/>
    <table  align="center">
        <tr>
            <td><label >员工名称：</label></td>
            <td><input type="text" name="empName" size="20"/></td>
        </tr>
        <tr>
            <td><label  >员工密码：</label></td>
            <td><input type="password" name="password" size="20"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交" id="input-submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>

