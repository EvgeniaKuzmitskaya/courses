<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 03.12.2017
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create an account</title>
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">

<div class="container">

<form id="registrationForm" action="/registration" method="post">
    <table style="with: 50%">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstname" /></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastname" /></td>
        </tr>
        <tr>
            <td>UserName</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
        </tr>
        <%--<tr>--%>
            <%--<td>Roles</td>--%>
            <%--<td><form action="roles" method="post">--%>
                <%--<p><select size="3" multiple name="">--%>
                    <%--<option disabled>Choose role</option>--%>
                    <%--<option value="Admin">ADMIN</option>--%>
                    <%--<option selected value="Teacher">TEACHER</option>--%>
                    <%--<option value="Student">STUDENT</option>--%>
                <%--</select></p>--%>
            <%--</form></td>--%>
        <%--</tr>--%>
        <tr>
            <td>Roles</td>
            <td>
                <form:select path="roles" items="${roles}" multiple="true" itemValue="idRole" itemLabel="typeRole" />
            </td>
        </tr>
    <%--<input type="submit" value="Submit" />--%>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.idUser}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>" />
                </c:if>
                <c:if test="${empty user.idUser}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>" />
                </c:if>
            </td>
        </tr>



    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<%--<h2>Add User</h2>--%>
<%--<form:form id="registrationForm" action="/registration" method="post">--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td><form:label path="idUser">ID:</form:label></td>--%>
            <%--&lt;%&ndash;<td><form:input path="idUser" value="${us.idUser}" readonly="true"/></td>&ndash;%&gt;--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="firstname">First Name:</form:label></td>--%>
            <%--&lt;%&ndash;<td><form:input path="firstname" value="${us.firstName}"/></td>&ndash;%&gt;--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="lastname">Last Name:</form:label></td>--%>
            <%--&lt;%&ndash;<td><form:input path="lastname" value="${us.lastName}"/></td>&ndash;%&gt;--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="login">User Name:</form:label></td>--%>
            <%--&lt;%&ndash;<td><form:input path="login" value="${us.login}"/></td>&ndash;%&gt;--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td><form:label path="password">Password:</form:label></td>--%>
            <%--&lt;%&ndash;<td><form:input path="password"  value="${us.password}"/></td>&ndash;%&gt;--%>
        <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><form:label path="email">Email:</form:label></td>--%>
                <%--&lt;%&ndash;<td><form:input path="email"  value="${us.email}"/></td>&ndash;%&gt;--%>
            <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="2"><input type="submit" value="Submit"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Roles</td>--%>
            <%--<td>--%>
                <%--<form:select path="roles" items="${roles}" multiple="true" itemValue="idRole" itemLabel="typeRole" />--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>

<%--<c:if test="${!empty users}">--%>
    <%--<c:if test="${!empty rolesList}">--%>
    <%--<h2>List Employees</h2>--%>
    <%--<table align="left" border="1">--%>
        <%--<tr>--%>
            <%--<th>FirstName</th>--%>
            <%--<th>LastName</th>--%>
            <%--<th>Email</th>--%>
            <%--<th>Role</th>--%>
        <%--</tr>--%>

    <%--<c:forEach items="${users}" var="user">--%>
        <%--<c:forEach items="${rolesList}" var="role">--%>
            <%--<tr>--%>
                <%--<td><c:out value="${us.firstName}"/></td>--%>
                <%--<td><c:out value="${us.lastName}"/></td>--%>
                <%--<td><c:out value="${us.email}"/></td>--%>
                <%--<td><c:out value="${role.typeRole}"/></td>--%>
                <%--<td align="center"><a href="/edit/${user.idUser}">Edit</a> |--%>
                    <%--<a href="/delete/${user.idUser}">Delete</a></td>--%>

            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</c:forEach>--%>
    <%--</table>--%>
    <%--</c:if>--%>
<%--</c:if>--%>


</div>

</body>
</html>