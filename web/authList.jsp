<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/top.css" type="text/css" rel="stylesheet">
    <link href="/main.css" type="text/css" rel="stylesheet">
    <title>权限管理</title>
</head>
<body>
<h2 align="center"><font color=red>权限管理</font></h2>
<a href="/top.jsp">Top首页</a>|
<table id="gridView" border="1px solid #ccc" cellspacing="0" cellpadding="0">
    <tr>
        <td scope=col>权限ID</td>
        <td scope=col>权限名称</td>
        <td scope=col>权限URL</td>
        <td scope=col>权限action</td>
        <td scope=col>操作</td>
    </tr>
    <c:forEach var="auth" items="${authList}" varStatus="status">
        <tr>
            <td>${auth.id}</td>
            <td>${auth.authName}</td>
            <td>${auth.url}</td>
            <td>${auth.actionName}</td>
            <td>
                <a href="/jt/userServlet?authId=${auth.id}&action=delAuth">删除</a>|
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4" align="left"><a href="/addAuth.jsp">>>添加权限</a></td>
    </tr>
</table>

</body>
</html>

