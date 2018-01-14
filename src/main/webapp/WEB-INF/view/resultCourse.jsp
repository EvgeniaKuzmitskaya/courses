<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 25.12.2017
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Result</title>
    <meta charset= "utf-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">

<div class="generic-container">

    <table>
        <tr>
            <td class="title lead">
                Put the rating |
                <a href="${pageContext.request.contextPath}/teacherPage">Teacher page</a>
            </td>
        </tr>
    </table>
    <br/>

<form id="resultForm"  class="form-horizontal" action="/resultCourse" modelAttribute="resultto" method="post">


    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Название курса:</label>
            <div class="col-md-7">
                <form:select path="courses" class="form-control input-sm">
                    <form:option value="0" label="select course"></form:option>
                    <form:options items="${courses}" multiple="true" itemValue="idCourse" itemLabel="nameCourse" class="form-control input-sm"/>
                </form:select>
                <div class="has-error">
                    <form:errors path="courses" class ="help-inline" />
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Студент: </label>
            <div class="col-md-7">
                <form:select name="userId" path="users" class="form-control input-sm">
                    <form:option value="0" label="select student"></form:option>
                    <form:options items="${users}" multiple="true" itemValue="idUser" itemLabel="lastName" class="form-control input-sm"/>
                </form:select>
                <div class="has-error">
                    <form:errors path="courses" class ="help-inline" />
                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Оценка</label>
            <div class="col-md-7">
                <form:select path="result" class="form-control input-sm">
                    <option value="0">select mark</option>
                    <option value="1">2</option>
                    <option value="2">3</option>
                    <option value="3">4</option>
                    <option value="4">5</option>
                    <option value="5">6</option>
                    <option value="6">7</option>
                    <option value="7">8</option>
                    <option value="8">9</option>
                    <option value="9">10</option>
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" value="Завершить курс" class="btn btn-primary btn-sm"/>
        </div>
    </div>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>
</div>
</body>
</html>
