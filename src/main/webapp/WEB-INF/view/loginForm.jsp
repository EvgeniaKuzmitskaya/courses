<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 03.12.2017
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset= "utf-8">
</head>

<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">

<%--<body onload='document.loginForm.username.focus();'>--%>
<h3 class="text-center">Авторизация</h3>

<%--<c:if test="${not empty error}"><div>${error}</div></c:if>--%>
<%--<c:if test="${not empty message}"><div>${message}</div></c:if>--%>

<div class="container">
    <form name="loginForm" method="POST" action="${contextPath}/loginUrl" class="form-signin">
        <c:if test="${param.error != null}">
            <i class="has-error">Sorry! You entered invalid username/password.</i>
        </c:if>
        <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
        <input name="password" type="password" class="form-control" placeholder="Password" />

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<!-- if this is login for update, ignore remember me check -->
<c:if test="${empty loginUpdate}">
        <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/> <!-- Флажок "запомнить меня" -->
        <label for="remember_me" class="inline">Remember me</label>
</c:if>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>

    <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
        <%--<h4 class="text-center"><a href="${contextPath}/admin/registration">Create an account</a></h4>--%>
    <%--</sec:authorize>--%>

    </form>
</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/static/js/bootstrap.min.js"></script>
</body>
</html>


