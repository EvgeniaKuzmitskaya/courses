<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 03.02.2018
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error</title>
    <meta charset="UTF-8">
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">
<div class="generic-container">

<div class="title">
    <div class="lead ">${username_error}</div>
</div>


<div>
     <a href="${contextPath}/registration">Попробуйте снова</a>
</div>
</div>
</body>
</html>
