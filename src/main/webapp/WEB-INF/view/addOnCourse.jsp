
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 25.12.2017
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>RegistrationCourse</title>
    <meta charset= "utf-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript" ></script>

<div class="generic-container">

    <table>
        <tr>
            <td class="title lead">
                Register to the course |
                <a href="${pageContext.request.contextPath}/studentPage">Student page</a>
            </td>
        </tr>
    </table>
    <br/>

<form id="courseRegister"  action="/registerCourse" modelAttribute="register" method="post" class="form-horizontal">

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Название курса:</label>
            <div class="col-md-7">
                <form:select id="courseId" path="studentCourses" class="form-control input-sm">
                <form:option value="0" label="select course"></form:option>
                <form:options items="${studentCourses}" multiple="true" itemValue="idCourse" itemLabel="nameCourse" class="form-control input-sm"/>
                </form:select>
                <div class="has-error">
                <form:errors path="studentCourses" class ="help-inline" />
                </div>
            </div>
        </div>
    </div>

    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable">Начало:</label>--%>
            <%--<div class="col-md-7 course-date-begin">--%>
                <%--<form:select id="courseDateBegin" path="datesBegin" class="form-control input-sm">--%>
                    <%--<form:option value="0" label="select date begin"></form:option>--%>
                    <%--<form:options items="${datesBegin}" multiple="true" itemValue="idCourse" itemLabel="dateBeginCourse" class="form-control input-sm"/>--%>
                <%--</form:select>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="datesBegin" class ="help-inline" />--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Начало: </label>
            <div class="col-md-7 course-date-begin">
                <input type="text" name="dateBeginCourse" class="form-control input-sm"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Конец: </label>
            <div class="col-md-7 course-date-end">
                <input type="text" name="dateEndCourse" class="form-control input-sm"/>
            </div>
        </div>
    </div>



    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<label class="col-md-3 control-lable">Конец:</label>--%>
            <%--<div class="col-md-7 course-date-end" >--%>
                <%--<form:select id="courseDateEnd" path="datesEnd" class="form-control input-sm">--%>
                    <%--<form:option value="0" label="select date end"></form:option>--%>
                    <%--<form:options items="${datesEnd}" multiple="true" itemValue="idCourse" itemLabel="dateEndCourse" class="form-control input-sm"/>--%>
                <%--</form:select>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="datesBegin" class ="help-inline" />--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>


    <%--<div class="row">--%>
        <%--<div class="form-group col-md-12">--%>
            <%--<form:label class="col-md-3 control-lable" path="users">Преподаватель:</form:label>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:select id="teacherId" name="userId" path="teachers" class="form-control">--%>
                    <%--<form:option value="0" label="select teacher"></form:option>--%>
                   <%--<form:options items="${teachers}" multiple="true" itemValue="idUser" itemLabel="lastName" class="form-control input-sm" />--%>
                <%--</form:select>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="teachers" class="help-inline" />--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Преподаватель: </label>
            <div class="col-md-7 name-teacher">
                <input type="text" name="userLastName" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <input type="hidden" name="idCourse" id="idCourseHidden"/>



    <div class="row">
        <div class="form-actions floatRight">
            <input type="submit" value="Зарегистрироваться на курс" class="btn btn-primary btn-sm"/>
        </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#courseId').change(function(){
        var courseId = $(this).val();
            if (courseId == '0') {
                $('#courseDateBegin').html('<option>select date begin</option>');
                $('#courseDateBegin').attr('disabled', true);
                $('#courseDateEnd').html('<option>select date end</option>');
                $('#courseDateEnd').attr('disabled', true);
                $('#teacherId').html('<option>select teacher</option>');
                $('#teacherId').attr('disabled', true);
                return (false);
            }
            $('#courseDateBegin').attr('disabled', true);
            $('#courseDateBegin').html('<option>загрузка...</option>');
            $('#courseDateEnd').attr('disabled', true);
            $('#courseDateEnd').html('<option>загрузка...</option>');
            $('#teacherId').attr('disabled', true);
            $('#teacherId').html('<option>загрузка...</option>');
            var url = "${pageContext.request.contextPath}/getCourses";

            /*
             * GET'овый AJAX запрос
             * подробнее о синтаксисе читайте
             * на сайте http://docs.jquery.com/Ajax/jQuery.get
             * Данные будем кодировать с помощью JSON
             */
            $.get(
                url,
                "courseId=" + courseId,
                function (result) {

                    $('.course-date-begin').html("<span>" + result.dateBeginCourse + "</span>");
                    $('.course-date-end').html("<span>" + result.dateEndCourse + "</span>");
                    $('.name-teacher').html("<span>" + result.userLastName + "</span>");
                    $('#idCourseHidden').val(result.idCourse);

//                    if (result.teachers) {
//                        $('#teacherId').html(result.teachers.map(function (user) {
//                            return "<option "+ "value=\"" + user.idUser + "\"" + ">" + user.lastName + "</option>";
//                        }).join(""));
//                    }


                    console.log(result);
                },
                "json"
            );
        });
    });



</script>

</body>
</html>
