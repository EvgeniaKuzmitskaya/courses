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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_STUDENT')">
    <form id="regCourse"  action="/regCourse" method="POST" modelAttribute = "reg">

        <label>Название курса</label>
        <input type="text" id = "nameCourse" name="nameCourse" required>

        <label>Начало</label>
        <input type="date" id="dateBeginCourse" name="dateBeginCourse" placeholder="dd/MM/yyyy" required>

        <label>Конец</label>
        <input type="date" id="dateEndCourse" name="dateEndCourse" placeholder="dd/MM/yyyy" required>

        <label>Профессор</label>
        <input type="textarea" id="descriptionCourse" name="descriptionCourse">

        <input type="submit" value="Добавить курс" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


        <div>
            <input type="submit" value="Зарегистрироваться на курс"/>
        </div>
    </form>

</sec:authorize>
</body>
</html>
