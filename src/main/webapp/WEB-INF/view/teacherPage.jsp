
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 31.12.2017
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>teacherPage</title>
    <meta charset="UTF-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">

<!-- Только при обладании -->
<!-- привилегией ROLE_TEACHER -->
<%--<sec:authorize access="hasRole('ROLE_TEACHER')">--%>
<div class="generic-container">
    <div class="title">
        <div class="lead">${title}</div>
    </div>

    <%--<c:url value='/logout' var="logoutUrl" />--%>
    <%--<form id="logout" action="${logoutUrl}" method="post" >--%>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
    <%--</form>--%>
    <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
        <%--User: ${pageContext.request.userPrincipal.name} |--%>
        <%--<a href="javascript:document.getElementById('logout').submit()">Выход</a>--%>
    <%--</c:if>--%>

    <c:if test="${userName != null}">
        User: <strong>${userName}</strong> /
        <a href="<c:url value="/logout" />"> Выход</a>
    </c:if>

    <div>
        <table align="center">
            <tr>
                <td>
                    <a href="${contextPath}/courseTeacher">All my courses</a> |
                    <a href="${contextPath}/putResult">Put the rating</a>
                </td>
            </tr>
        </table>
    </div>

</div>
<%--</sec:authorize>--%>
</body>
</html>
