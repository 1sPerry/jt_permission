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
    <title>分配按钮权限</title>
</head>
<body>
<h2 align="center"><font color=red>分配按钮权限：</font></h2>
<form name="form2" method="post" action="/jt/userServlet?action=assignBtnAdd">
    <input type="text" name="roleId"  value="${roleId}" >
    <table align="center">
        <tr>
            <td><label>按钮名称：</label></td>
<%-- <td>${roleName}</td>--%>
            <td>
                <c:forEach var="btn" items="${btns}">
                    <input type="checkbox" name="btnIds" id="" value="${btn.id }"

                            <c:forEach items="${btnList }" var="btnInfo">
                                <c:if test="${btn.id==btnInfo.id }">
                                    checked=true
                                </c:if>
                            </c:forEach>
                    /> ${btn.btnName } : ${btn.des }<br>
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

