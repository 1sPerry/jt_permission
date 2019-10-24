<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/top.css" type="text/css" rel="stylesheet">
    <link href="/main.css" type="text/css" rel="stylesheet">
    <title>添加权限</title>
</head>
<body>
<h2 align="center"><font color=red>添加权限</font></h2>
<form name="form2" method="post" action="/jt/userServlet?action=addAuth" >
    <input type="hidden" name="action" value="save"/>
    <table  align="center">
        <tr>
            <td><label >权限名称：</label></td>
            <td><input type="text" name="authName" size="20"/></td>
        </tr>
        <tr>
            <td><label >权限URL：</label></td>
            <td><input type="text" name="url" size="20"/></td>
        </tr>
        <tr>
            <td><label >权限action：</label></td>
            <td><input type="text" name="actionName" size="20"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交" id="input-submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>

