<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/top.css" type="text/css" rel="stylesheet">
    <link href="/main.css" type="text/css" rel="stylesheet">
    <title>分配角色</title>
</head>
<body>
<h2 align="center"><font color=red>分配角色</font></h2>
<form name="form2" method="post" action="/jt/userServlet?action=assignRole">
    <table align="center">
        <tr>
            <td><label>员工名称：</label></td>
            <td>${empName}</td>

            <td>
                <select name="mae" id="mae" size="20" style="width:120px;height:50px;" multiple="multiple">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.id}">${role.roleName}</option>
                        <%--<c:choose>
                             <c:when test="${role.roleName == roleInfo.roleName}">
                            <option value="${role.id}" selected="selected">${role.roleName}</option>
                             </c:when>
                            <c:otherwise>
                                <option value="${role.id}">${role.roleName}</option>
                            </c:otherwise>
                        </c:choose>--%>
                    </c:forEach>
                </select>
            </td>


        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交" id="input-submit"/></td>
        </tr>
    </table>
</form>
</body>

</html>

