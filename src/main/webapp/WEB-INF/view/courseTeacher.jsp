<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 02.01.2018
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>courseTeacher</title>
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
                <a href="${pageContext.request.contextPath}/teacherPage">Teacher page</a> |
                <a href="${pageContext.request.contextPath}/resultCourse">Put the rating</a> |
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
            <th width="100"><strong>Студент</strong></th>
            <th width="70"><strong>Оценка</strong></th>
        </tr>

        <c:forEach items="${listSudentCourse}" var="course">
            <tr>
                <td>${course.nameCourse}</td>
                <td>${course.dateBeginCourse}</td>
                <td>${course.dateEndCourse}</td>
                <td>${course.userLastName}</td>
                <td>${course.markResult}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
