<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 03.12.2017
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>registry</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">

<div class="container">


<form id="registrationForm"  class="form-signin" action="/registration" modelAttribute="user" method="post">
    <div class="form-group">
    <table style="with: 50%">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" class="form-control" /></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" class="form-control"/></td>
        </tr>
        <tr>
            <td>UserName</td>
            <td><input type="text" name="userName" class="form-control"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" class="form-control"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="passwordConfirm" class="form-control"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email" class="form-control"/></td>
        </tr>
        <tr>
            <td>Select role:</td>
            <td><form:select name="roleId" path="roles">
                <form:options items="${roles}" multiple="true" itemValue="idRole" itemLabel="typeRole" />
            </form:select></td>
            <td><form:errors path="roles" cssClass="error" /></td>
        </tr>
        <tr>
            <td>
                <%--<c:if test="${!empty user.idUser}">--%>
                    <%--<input type="submit"--%>
                           <%--value="<spring:message text="Edit User"/>" />--%>
                <%--</c:if>--%>
                <%--<c:if test="${empty user.idUser}">--%>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Add User</button>
                    <%--<input type="submit"--%>
                           <%--value="<spring:message text="Add User"/>" />--%>
                <%--</c:if>--%>
            </td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </div>
</form>
</div>
</body>
</html>