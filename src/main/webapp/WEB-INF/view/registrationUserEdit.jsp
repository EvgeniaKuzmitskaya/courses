<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 01.02.2018
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>UserEdit</title>
    <meta charset="UTF-8">
</head>
<body>
<link href="<c:url value='/static/css/list.css' />" rel="stylesheet">
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">

<div class="container">
    <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
    <table>
        <tr>
            <td class="title lead">
                User Edit Form |
                <a href="${contextPath}/adminPage">Home page</a>
            </td>
        </tr>
    </table>
    <br/>

<form:form method="POST" action="/edit/{id}" class="form-horizontal" accept-charset="UTF-8">

    <div class="row">
        <div class="form-group col-md-12">
             <div class="col-md-7">
                <form:hidden  path="idUser" class="form-control input-sm" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">FirstName</label>
            <div class="col-md-7">
                <form:input path="firstName" class="form-control input-sm" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Last Name</label>
            <div class="col-md-7">
                <form:input path="lastName" class="form-control input-sm"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">UserName</label>
            <div class="col-md-7">
                <form:input path="userName" class="form-control input-sm" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Password</label>
            <div class="col-md-7">
                <form:input path="password" class="form-control input-sm"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Email</label>
            <div class="col-md-7">
                <form:input path="email" class="form-control input-sm"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Role:</label>
            <div class="col-md-7">
                <%--<form:input path="typeRole" class="form-control input-sm"/>--%>
                <form:select class="form-control input-sm" path="typeRole">
                <form:options items="${roles}" multiple="true" itemValue="idRole" itemLabel="typeRole" />
                </form:select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-actions floatRight">
            <button class="button-registration" type="submit">Сохранить</button>
        </div>
    </div>

    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>







<%--<form:form method="POST" action="/edit/{id}" class="form-horizontal">--%>

    <%--<br/>--%>
    <%--<br/>--%>
    <%--<br/>--%>

    <%--<table>--%>
        <%--<tr>--%>
            <%--<td></td>--%>
            <%--<td><form:hidden  path="idUser" /></td>--%>
        <%--</tr>--%>


        <%--<tr>--%>
            <%--<td>Login : </td>--%>
            <%--<td><form:input path="userName" class="form-control input-sm" /></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td>Email : </td>--%>
            <%--<td><form:input path="email" class="form-control input-sm" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Password :</td>--%>
            <%--<td><form:input path="password" class="form-control input-sm"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Имя :</td>--%>
            <%--<td><form:input path="firstName" class="form-control input-sm"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Фамилия :</td>--%>
            <%--<td><form:input path="lastName" class="form-control input-sm"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>Role :</td>--%>
            <%--&lt;%&ndash;<td><form:input path="typeRole" class="form-control input-sm"/></td>&ndash;%&gt;--%>
            <%--<td><form:select class="form-control input-sm" name="roleId" path="typeRole">--%>
               <%--<form:options items="${roles}" multiple="true" itemValue="idRole" itemLabel="typeRole" />--%>
            <%--</form:select>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td> </td>--%>
            <%--<td><input type="submit" value="Save" /></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
</form:form>
    <%--</sec:authorize>--%>
</div>


</body>
</html>
