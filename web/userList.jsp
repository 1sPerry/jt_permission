<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@taglib prefix="btn" uri="http://test.com" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/top.css" type="text/css" rel="stylesheet">
    <link href="/main.css" type="text/css" rel="stylesheet">
    <title>用户列表</title>
</head>
<body>
<h2 align="center"><font color=red>用户列表</font></h2>
<a href="/top.jsp">Top首页</a>|
<table id="gridView" border="1px solid #ccc" cellspacing="0" cellpadding="0">
    <tr>
        <td scope=col>员工ID</td>
        <td scope=col>员工名称</td>
        <td scope=col>员工职位</td>
        <td scope=col>操作</td>
    </tr>
    <c:forEach var="user" items="${userList}" varStatus="status">
        <tr>
            <td id="id">${user.id}</td>
            <td id="empName">${user.empName}</td>
            <td id="position">${user.position}</td>
            <td id="edit">
                <btn:btnPermission name="delUserBtn">
                    <a href="/jt/userServlet?userId=${user.id}&action=del">删除</a>|
                </btn:btnPermission>
                <btn:btnPermission name="assignRoleBtn">
                <a href="/jt/userServlet?userId=${user.id}&empName=${user.empName}&action=assignRole">分配角色</a>
                </btn:btnPermission>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <btn:btnPermission name="addUserBtn" >
            <td colspan="4" align="left"><a href="/addUser.jsp">>>添加用户</a></td>
        </btn:btnPermission>
    </tr>
</table>
</body>
</html>

