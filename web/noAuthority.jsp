<%--
  Created by IntelliJ IDEA.
  User: wangxiaopan
  Date: 2019/10/22
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限不足</title>
</head>
<body>
<h2 align="center"><font color=red>对不起，您没有权限操作！</font></h2>
<div id="top">
    <span><font color="red">${msg}</font></span>
</div>
</body>
</html>

