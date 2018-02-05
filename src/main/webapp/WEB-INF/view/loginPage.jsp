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

<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
</head>
<body onload='document.login.userName.focus();'>

<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">

    <h3 class="text-center">Вход</h3>

<div class="container">
    <form name="login" method="POST" action="${contextPath}/loginUrl" class="form-signin">

        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Invalid username and password.</p>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>You have been logged out successfully.</p>
            </div>
        </c:if>

        <input name="userName" type="text" class="form-control" placeholder="username" autofocus="true"/>
        <input name="password" type="password" class="form-control" placeholder="password" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/> <!-- Флажок "запомнить меня" -->
        <label for="remember_me" class="inline">Remember me</label>
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


