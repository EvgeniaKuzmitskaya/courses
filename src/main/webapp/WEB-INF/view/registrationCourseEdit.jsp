<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 02.02.2018
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>CourseEdit</title>
    <meta charset=UTF-8">
</head>
<body>

<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">

<div class="generic-container">
    <table>
        <tr>
            <td class="title lead">
                Edit course |
                <a href="${contextPath}/adminPage">Admin page</a>
            </td>
        </tr>
    </table>

    <br/>

<form:form method="POST" action="/editCourse/{id}" class="form-horizontal" accept-charset="UTF-8">

    <div class="row">
        <div class="form-group col-md-12">
            <div class="col-md-7">
                <form:hidden  path="idCourse" class="form-control input-sm" />
            </div>
        </div>
    </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Название курса</label>
                <div class="col-md-7">
                    <form:input  path="nameCourse" class="form-control input-sm"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Начало</label>
                <div class="col-md-7">
                    <form:input  path="dateBeginCourse" placeholder="dd.MM.yyyy" class="form-control input-sm" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Конец</label>
                <div class="col-md-7">
                    <form:input  path="dateEndCourse" placeholder="dd.MM.yyyy" class="form-control input-sm" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Преподаватель:</label>
                <div class="col-md-7">
                    <%--<form:input  path="userLastName" class="form-control input-sm" />--%>
                    <form:select path="userLastName" class="form-control">
                        <form:option value="0" label="select teacher"></form:option>
                        <form:options items="${users}" multiple="true" itemValue="idUser" itemLabel="lastName" class="form-control input-sm" />
                    </form:select>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="users" class="help-inline" />--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Описание курса</label>
                <div class="col-md-7">
                    <form:input  path="descriptionCourse" class="form-control input-sm" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Статус:</label>
                <div class="col-md-7">
                    <form:select path="typeStatus" class="form-control">
                        <form:option value="0" label="select status"></form:option>
                        <form:options items="${status}" multiple="true" itemValue="idStatus" itemLabel="typeStatus" class="form-control input-sm" />
                    </form:select>
                        <%--<form:input  path="typeStatus" class="form-control input-sm" />--%>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="status" class="help-inline" />--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-actions floatRight">
                <button class="btn btn-primary btn-sm" type="submit">Сохранить</button>
            </div>
        </div>
</form:form>
</div>
</body>
</html>
