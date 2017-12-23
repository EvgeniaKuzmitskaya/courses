<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Title</title>
</head>
<body>

<h2>${message}</h2>



<table>
    <tr>
        <td><strong>Название курса: </strong></td>
        <td><strong>Начало</strong></td>
        <td><strong>Конец</strong></td>
        <td><strong>Описание курса: </strong></td>
    </tr>
    <c:forEach items="${listCourse}" var="course">
    <tr>
        <td>${course.nameCourse}</td>
        <td>${course.dateBeginCourse}</td>
        <td>${course.dateEndCourse}</td>
        <td>${course.descriptionCourse}</td>
    </tr>
    </c:forEach>
</table>






<%--<h1>${message}</h1>--%>

<%--<table>--%>
    <%--<tr>--%>
        <%--<td>Название курса : ${course.courseName}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>Дата начала курса : ${course.dateBeginCourse}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>Дата конца курса : ${course.dateEndCourse}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>Описание курса : ${course.descriptionCourse}</td>--%>
    <%--</tr>--%>
<%--</table>--%>
</body>
</html>
