<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/top.css" type="text/css" rel="stylesheet">
    <link href="/main.css" type="text/css" rel="stylesheet">
    <title>角色管理</title>
</head>
<body>
<h2 align="center"><font color=red>角色管理</font></h2>
<a href="/top.jsp">Top首页</a>|
<table id="gridView" border="1px solid #ccc" cellspacing="0" cellpadding="0">
    <tr>
        <td scope=col>角色ID</td>
        <td scope=col>角色名称</td>
        <td scope=col>操作</td>
    </tr>
    <c:forEach var="role" items="${roleList}" varStatus="status">
        <tr>
            <td id="id">${role.id}</td>
            <td id="roleName">${role.roleName}</td>
            <td id="edit">
                <a href="/jt/userServlet?roleId=${role.id}&action=delRole">删除</a>|
                <a href="/jt/userServlet?roleId=${role.id}&roleName=${role.roleName}&action=assignAuth">分配权限</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4" align="left"><a href="/addRole.jsp">>>添加角色</a></td>
    </tr>
</table>

</body>
</html>

