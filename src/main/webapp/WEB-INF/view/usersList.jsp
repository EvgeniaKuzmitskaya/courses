<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 08.12.2017
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Users List</title>
    <meta charset= "utf-8">
</head>
<body>
<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/common.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/list.css' />" rel="stylesheet">

<div class="container">
    <table>
        <tr>
            <td class="title lead">
                List User |
                <a href="${pageContext.request.contextPath}/adminPage">Admin page</a> |
                <a href="${pageContext.request.contextPath}/registration">Add New User</a> |
            </td>
        </tr>
    </table>
<br/>
<br/>
    <c:if test="${!empty listUsers}">
    <table class="tg">
        <thead>
            <tr>
                <th width="60"><strong>ID</strong></th>
                <th width="120"><strong>Имя</strong></th>
                <th width="120"><strong>Фамилия</strong></th>
                <th width="120"><strong>Email</strong></th>
                <th width="100"><strong>Login</strong></th>
                <th width="80"><strong>Password</strong></th>
                <th width="80"><strong>Role</strong></th>
                <th width="60"><strong>Edit</strong></th>
                <th width="60"><strong>Delete</strong></th>
            </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.idUser}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.userName}</td>
                <td>${user.password}</td>
                <td>${user.typeRole}</td>

                <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
                <td><a href="<c:url value='/edit/${user.idUser}'/>" class="btn btn-success custom-width">Edit</a></td>
                <td><a href="<c:url value='/remove/${user.idUser}'/>" class="btn btn-danger custom-width">Delete</a></td>
                <%--</sec:authorize>--%>
            </tr>
        </c:forEach>
    </c:if>
    </table>
            <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
            <%--<div class="well">--%>
                <%--<a href="registration.jsp">Add New User</a>--%>
            <%--</div>--%>
            <%--</sec:authorize>--%>
</div>

</body>
</html>

