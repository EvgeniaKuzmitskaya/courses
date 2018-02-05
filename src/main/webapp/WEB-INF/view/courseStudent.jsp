<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 02.01.2018
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CourseStudent</title>
    <meta charset="UTF-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">
<link href="${contextPath}/static/css/list.css" rel="stylesheet">

<div class="container">

    <table>
        <tr>
            <td class="title lead">
                List of courses |
                <a href="${contextPath}/studentPage">Student page</a> |
                <a href="${contextPath}/registerCourse">Register to the course</a> |
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
            <th width="100"><strong>Преподаватель</strong></th>
            <th width="60"><strong>Статус</strong></th>
            <th width="60"><strong>Оценка</strong></th>
        </tr>

        <c:forEach items="${listSudentCourse}" var="course">
            <tr>
                <td>${course.nameCourse}</td>
                <td>${course.dateBeginCourse}</td>
                <td>${course.dateEndCourse}</td>
                <td>${course.userLastName}</td>
                <td>${course.typeStatus}</td>
                <td>${course.markResult}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
