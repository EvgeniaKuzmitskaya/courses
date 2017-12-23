<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 03.12.2017
  Time: 20:19
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
    <title>Login</title>
</head>

<body onload='document.loginForm.username.focus();'>
<h3 class="text-center">Enter the system</h3>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">


<%--<c:if test="${not empty error}"><div>${error}</div></c:if>--%>
<%--<c:if test="${not empty message}"><div>${message}</div></c:if>--%>


<%--<form name='login' action="<c:url value='/loginForm'/>" method="post">--%>

    <%--<table>--%>
        <%--<tr>--%>
            <%--<td>UserName:</td>--%>
            <%--<td><input type='text' name='username' value=''></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Password:</td>--%>
            <%--<td><input type='password' name='password' /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan='2'><input name="submit" type="submit" value="submit" /></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>

<%--</form>--%>
<%--<a href='/registration'>Create an account</a>--%>

<div class="container">

    <form method="POST" action="${contextPath}/loginForm" class="form-signin">
        <%--<h2 class="form-heading">Log in</h2>--%>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input id="remember_me" name="_spring_security_remember_me"
                   type="checkbox"/> <!-- Флажок "запомнить меня" -->
            <label for="remember_me"
                   class="inline">Remember me</label>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>
    </form>
</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/static/js/bootstrap.min.js"></script>
    </body>
</html>


