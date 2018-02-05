<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 19.12.2017
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>CoursesAdded</title>
    <meta charset="UTF-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">
<link href="${contextPath}/static/css/list.css" rel="stylesheet">

<div class="container">

    <div class="d9">${message}</div>

    <table>
        <tr>
            <td class="title lead">
                List of courses |
                <a href="${contextPath}/adminPage">Admin page</a> |
                <a href="${contextPath}/courseForm">Add a new course</a> |
            </td>
        </tr>
    </table>
<br/>
<br/>

<table class="tg">
    <tr>
        <th width="170"><strong>Название курса</strong></th>
        <th width="85"><strong>Начало</strong></th>
        <th width="85"><strong>Конец</strong></th>
        <th width="180"><strong>Описание курса</strong></th>
        <th width="100"><strong>Преподаватель</strong></th>
        <th width="60"><strong>Статус</strong></th>
        <th width="70"><strong>Edit</strong></th>
        <th width="70"><strong>Delete</strong></th>
    </tr>

    <c:forEach items="${listCourse}" var="course">
        <tr>
        <td>${course.nameCourse}</td>
        <td>${course.dateBeginCourse}</td>
        <td>${course.dateEndCourse}</td>
        <td>${course.descriptionCourse}</td>
        <td>${course.userLastName}</td>
        <td>${course.typeStatus}</td>
        <td><a href="<c:url value='/editCourse/${course.idCourse}'/>" class="btn btn-success custom-width">Edit</a></td>
        <td><a href="<c:url value='/removeCourse/${course.idCourse}'/>" class="btn btn-danger custom-width">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
