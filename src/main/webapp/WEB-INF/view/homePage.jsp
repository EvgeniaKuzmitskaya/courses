
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 08.12.2017
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>




<%--<a href="javascript:document.getElementById('logout').submit()">Logout</a>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
<a href="${pageContext.request.contextPath}/userPage">JD User</a> |
<a href="${pageContext.request.contextPath}/adminPage">JD Admin</a>

<h3>Welcome to JournalDEV Tutorials</h3>

<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">



<ul>
    <li>Java 8 tutorial</li>
    <li>Spring tutorial</li>
    <li>Gradle tutorial</li>
    <li>BigData tutorial</li>
</ul>

<c:url value='/logout' var="logoutUrl" />

<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
</div>
</body>
</html>
