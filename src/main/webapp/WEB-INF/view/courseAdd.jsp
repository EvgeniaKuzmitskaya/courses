<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 08.12.2017
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta charset=UTF-8">
    <title>AddCourse</title>
</head>
<body>

<h2>Добавление нового курса</h2>

<form id="courseAdd"  action="/courseForm" method="POST" modelAttribute = "courseto">

    <label>Название курса</label>
    <input type="text" id = "nameCourse" name="nameCourse" required>

    <label>Начало</label>
    <input type="date" id="dateBeginCourse" name="dateBeginCourse" placeholder="dd.MM.yyyy" required>

    <label>Конец</label>
    <input type="date" id="dateEndCourse" name="dateEndCourse" placeholder="dd.MM.yyyy" required>

    <tr>
        <td>Преподаватель:</td>
        <td>
            <form:select name="userId" path="users">
            <form:options items="${users}" multiple="true" itemValue="idUser" itemLabel="lastName" />
        </form:select></td>
        <td><form:errors path="users" cssClass="error" /></td>
    </tr>



    <label>Описание курса</label>
    <input type="textarea" id="descriptionCourse" name="descriptionCourse">

    <input type="submit" value="Добавить курс" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>


<%--@elvariable id="course" type=""--%>
<%--<form:form method="POST" action="/courseForm" modelAttribute="course">--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td><form:label path="nameCourse">Название курса:</form:label></td>--%>
            <%--<td><form:input path="nameCourse" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="dateBeginCourse">Начало:</form:label></td>--%>
            <%--<td><form:input path="dateBeginCourse" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="dateEndCourse">Конец:</form:label></td>--%>
            <%--<td><form:input path="dateEndCourse" /></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td>Преподаватель:</td>--%>
            <%--<td><form:select name="userId" path="users">--%>
                <%--<form:options items="${users}" multiple="true" itemValue="idUser" itemLabel="lastName" />--%>
            <%--</form:select></td>--%>
            <%--<td><form:errors path="users" cssClass="error" /></td>--%>
        <%--</tr>--%>


        <%--<tr>--%>
            <%--<td><form:label path="descriptionCourse"> Описание курса:</form:label></td>--%>
            <%--<td><form:input path="descriptionCourse"  /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="2"><input type="submit" value="Добавить курс"/></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
<%--</form:form>--%>

<%--<h2>${message}</h2>--%>
<%--<table>--%>
    <%--<tr>--%>
        <%--<td><strong>Название курса: </strong></td>--%>
        <%--<td><strong>Начало</strong></td>--%>
        <%--<td><strong>Конец</strong></td>--%>
        <%--<td><strong>Описание курса: </strong></td>--%>
    <%--</tr>--%>
    <%--<c:forEach items="${listCourse}" var="course">--%>
        <%--<tr>--%>
            <%--<td>${course.courseName}</td>--%>
            <%--<td>${course.dateBeginCourse}</td>--%>
            <%--<td>${course.dateEndCourse}</td>--%>
            <%--<td>${course.descriptionCourse}</td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>



</body>
</html>
