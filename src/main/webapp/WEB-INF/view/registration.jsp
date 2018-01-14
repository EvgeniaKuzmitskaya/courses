<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 03.12.2017
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>registry</title>
    <meta charset= "utf-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">

<div class="generic-container">

    <table>
        <tr>
            <td class="title lead">
                User Registration Form |
                <a href="${pageContext.request.contextPath}/adminPage">Home page</a>
            </td>
        </tr>
    </table>
    <br/>

<form id="registrationForm" action="/registration" modelAttribute="user" method="post" class="form-horizontal">

<div class="row">
    <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">FirstName</label>
       <div class="col-md-7">
           <input type="text" name="firstName" class="form-control input-sm"/>
       </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
        <label class="col-md-3 control-lable">Last Name</label>
        <div class="col-md-7">
        <input type="text" name="lastName" class="form-control input-sm"/>
       </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
        <label class="col-md-3 control-lable">UserName</label>
        <div class="col-md-7">
        <input type="text" name="userName" class="form-control input-sm"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
        <label class="col-md-3 control-lable">Password</label>
        <div class="col-md-7">
        <input type="password" name="password" class="form-control input-sm"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
        <label class="col-md-3 control-lable">Email</label>
        <div class="col-md-7">
        <input type="email" name="email" class="form-control input-sm"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group col-md-12">
    <label class="col-md-3 control-lable">Select role:</label>
        <div class="col-md-7">
        <form:select class="form-control input-sm" name="roleId" path="roles">
            <form:option value="0" label="select role"></form:option>
        <form:options items="${roles}" multiple="true" itemValue="idRole" itemLabel="typeRole" />
        </form:select>
        <div class="has-error">
        <form:errors path="roles" class ="help-inline" />
        </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-actions floatRight">

                <%--<c:if test="${!empty user.idUser}">--%>
                    <%--<input type="submit"--%>
                           <%--value="<spring:message text="Edit User"/>" />--%>
                <%--</c:if>--%>
                <%--<c:if test="${empty user.idUser}">--%>
                    <button class="button-registration" type="submit">Register</button>
                    <%--<input type="submit"--%>
                           <%--value="<spring:message text="Add User"/>" />--%>
                <%--</c:if>--%>
            </div>
        </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>
</div>
</body>
</html>