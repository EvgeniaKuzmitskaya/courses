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
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="container">
    <%--<%@include file="authheader.jsp" %>--%>
    <%--<div class="panel panel-default">--%>

        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users</span></div>

        <c:if test="${!empty listUsers}">
    <table border="1">
        <thead>
            <tr>
                <th width="60">ID</th>
                <th width="120">First Name</th>
                <th width="120">Last Name</th>
                <th width="120">Email</th>
                <th width="120">Login</th>
                <th width="120">Password</th>
                <%--<th width="120">Role</th>--%>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.idUser}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.userName}</td>
                <td>${user.password}</td>
                <%--<td>${user.roles}</td>--%>
                <td><a href="<c:url value='/edit/${user.idUser}'/>" class="btn btn-success custom-width">Edit</a></td>
                <td><a href="<c:url value='/remove/${user.idUser}'/>" class="btn btn-danger custom-width">Delete</a></td>
            </tr>
        </c:forEach>
    </c:if>
    </table>
            <%--<sec:authorize access="hasRole('ADMIN')">--%>
            <%--<div class="well">--%>
                <a href="registration.jsp">Add New User</a>
            <%--</div>--%>
            <%--</sec:authorize>--%>
    <%--<h3><a href="registration.jsp">Add More Users</a></h3>--%>
    <%--</div>--%>

</div>
</body>
</html>

