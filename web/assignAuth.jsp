<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/top.css" type="text/css" rel="stylesheet">
    <link href="/main.css" type="text/css" rel="stylesheet">
    <title>分配权限</title>
</head>
<body>
<h2 align="center"><font color=red>分配权限</font></h2>
<form name="form2" method="post" action="/jt/userServlet?action=assignAuth">
    <table align="center">
        <tr>
            <td><label>角色名称：</label></td>
<%--            <td>${empName}</td>--%>

            <td>
                this：
              权限 多选 list 遍历
            </td>


        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交" id="input-submit"/></td>
        </tr>
    </table>
</form>
</body>

</html>

