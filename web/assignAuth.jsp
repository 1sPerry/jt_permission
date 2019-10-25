<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<form name="form2" method="post" action="/jt/userServlet?action=assignAuthAdd">
    <input type="text" name="roleId"  value="${roleId}" >
    <table align="center">
        <tr>
            <td><label>权限名称：</label></td>
 <td>${empName}</td>
            <td>
                <c:forEach var="auth" items="${authListAll}">
                    <input type="checkbox" name="authIds" id="" value="${auth.id }"

                            <c:forEach items="${authList }" var="authInfo">
                                <c:if test="${auth.id==authInfo.id }">
                                    checked=true
                                </c:if>
                            </c:forEach>
                    />${auth.authName }<br>
                </c:forEach>
            </td>


        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="提交" id="input-submit"/></td>
        </tr>
    </table>
</form>
</body>

</html>

