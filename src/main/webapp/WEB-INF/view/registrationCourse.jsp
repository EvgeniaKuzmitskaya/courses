<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <title>AddCourse</title>
    <meta charset=UTF-8">
</head>
<body>

<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">

<div class="generic-container">
    <table>
        <tr>
            <td class="title lead">
            Add a new course |
        <a href="${contextPath}/adminPage">Admin page</a>
        </td>
        </tr>
    </table>

<br/>
<form id="courseAdd"  action="/courseForm" method="POST" modelAttribute = "courseto" class="form-horizontal" accept-charset="UTF-8">

<div class="row">
    <div class="form-group col-md-12">
        <label class="col-md-3 control-lable">Название курса</label>
        <div class="col-md-7">
            <input type="text" id = "nameCourse" name="nameCourse" class="form-control input-sm" required>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
        <label class="col-md-3 control-lable">Начало</label>
        <div class="col-md-7">
            <input type="date" id="dateBeginCourse" name="dateBeginCourse" placeholder="dd.MM.yyyy" class="form-control input-sm" required>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
        <label class="col-md-3 control-lable">Конец</label>
        <div class="col-md-7">
            <input type="date" id="dateEndCourse" name="dateEndCourse" placeholder="dd.MM.yyyy" class="form-control input-sm" required>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
        <form:label class="col-md-3 control-lable" path="users">Преподаватель:</form:label>
        <div class="col-md-7">
            <form:select name="userId" path="users" class="form-control">
                <form:option value="0" label="select teacher"></form:option>
            <form:options items="${users}" multiple="true" itemValue="idUser" itemLabel="lastName" class="form-control input-sm" />
            </form:select>
            <div class="has-error">
                <form:errors path="users" class="help-inline" />
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
    <label class="col-md-3 control-lable">Описание курса</label>
        <div class="col-md-7">
            <input type="textarea" id="descriptionCourse" name="descriptionCourse" class="form-control input-sm">
        </div>
    </div>
</div>
<div class="row">
     <div class="form-group col-md-12">
     <form:label path="status" class="col-md-3 control-lable">Статус:</form:label></td>
         <div class="col-md-7">
            <form:select name="statusId" path="status" class="form-control">
            <form:option value="0" label="select status"></form:option>
            <form:options items="${status}" multiple="true" itemValue="idStatus" itemLabel="typeStatus" class="form-control input-sm" />
            </form:select></td>
                <div class="has-error">
                    <form:errors path="status" class="help-inline" />
                </div>
         </div>
     </div>
</div>
    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" value="Добавить курс" class="btn btn-primary btn-sm"/>
        </div>
    </div>


    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</div>
</body>
</html>
