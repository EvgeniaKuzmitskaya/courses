<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 12.12.2017
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset= "utf-8">
    <title>studentPage</title>
 </head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">

<%--<sec:authorize access="hasRole('ROLE_STUDENT')">--%>

<div class="generic-container">
    <div class="title">
        <div class="lead ">${title}</div>
    </div>

    <c:url value='/logout' var="logoutUrl"/>
    <form id="logout" action="${logoutUrl}" method="post" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        User: ${pageContext.request.userPrincipal.name} |
        <a href="javascript:document.getElementById('logout').submit()">Выход</a>
    </c:if>

        <div>
            <table align="center">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/courseStudent">My courses</a> |
                        <a href="${pageContext.request.contextPath}/registerCourse">Register to the course</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
<%--</sec:authorize>--%>


</body>
</html>
