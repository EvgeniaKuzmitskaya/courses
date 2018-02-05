<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 31.12.2017
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>403</title>
    <meta charset= "UTF-8">
</head>
<body>
<h1>HTTP Status 403 - Access is denied</h1>

<c:choose>
    <c:when test="${empty userName}">
        <h2>You do not have permission to access this page!</h2>
    </c:when>
    <c:otherwise>
        <h2>Username : ${userName} <br/>
            You do not have permission to access this page!</h2>
    </c:otherwise>
</c:choose>

</body>
</html>
