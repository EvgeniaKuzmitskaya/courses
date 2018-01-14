
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 08.12.2017
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>adminPage</title>
    <meta charset= "utf-8">
</head>

<body>
    <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/static/css/app.css" rel="stylesheet">
    <link href="${contextPath}/static/css/common.css" rel="stylesheet">
<%--<sec:authorize access="hasRole('ADMIN')">--%>
    <div class="generic-container">
        <div class="title">
            <div class="lead ">${title}</div>
        </div>

<c:url value='/logout' var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    User: ${pageContext.request.userPrincipal.name} |
    <a href="javascript:document.getElementById('logout').submit()">Выход</a>
</c:if>
<br/>
<br/>
    <div>
        <table align="center">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/registration">Register a new user</a> |
                    <a href="${pageContext.request.contextPath}/courseForm">Add a new course</a> |
                    <a href="${pageContext.request.contextPath}/listCourses">List of courses</a> |
                    <a href="${pageContext.request.contextPath}/usersList">List of users</a>
                </td>
            </tr>
        </table>
    </div>
    </div>
<%--</sec:authorize>--%>

    <sec:authorize access="isRememberMe()">
        <h2># This user is login by "Remember Me Cookies".</h2>
    </sec:authorize>

    <sec:authorize access="isFullyAuthenticated()">
        <h2># This user is login by username / password.</h2>
    </sec:authorize>


</body>
</html>
