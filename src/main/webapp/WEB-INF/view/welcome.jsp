<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 06.12.2017
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>welcome</title>
</head>

<body>

    <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/static/css/common.css" rel="stylesheet">

<div class="container">
     <h3 align="center">Welcome to the faculty page</h3>
     <table align="center">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/loginForm">Log in</a> |
                <a href="${pageContext.request.contextPath}/registration">Sign up</a> |
                <a href="${pageContext.request.contextPath}/courseForm">Courses</a>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
