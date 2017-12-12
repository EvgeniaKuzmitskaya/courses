<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 08.12.2017
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<h1>List Users</h1>
<h3><a href="registration.jsp">Add More Users</a></h3>

<br>
<h3>Persons List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
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
                <td><a href="<c:url value='/edit/${user.idUser}'/>" >Edit</a></td>
                <td><a href="<c:url value='/remove/${user.idUser}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>

