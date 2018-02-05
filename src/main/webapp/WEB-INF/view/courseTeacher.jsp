<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: Evgenia
  Date: 02.01.2018
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>courseTeacher</title>
    <meta charset="UTF-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/common.css" rel="stylesheet">
<link href="${contextPath}/static/css/list.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript" ></script>

<div class="container">

    <table>
        <tr>
            <td class="title lead">
                List of students |
                <a href="${contextPath}/teacherPage">Teacher page</a> |
                <a href="${contextPath}/putResult">Put the rating</a> |
            </td>
        </tr>
    </table>
    <br/>
    <br/>

    <form id="chooseForm"  class="form-horizontal" action="/chooseCourse" modelAttribute="coursesChoose" method="post">
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable">Выберите курс:</label>
                <div class="col-md-7">
                    <form:select id="courseIdT" path="coursesTeacher" class="form-control input-sm">
                        <form:option value="0" label="select course"></form:option>
                        <form:options items="${coursesTeacher}" multiple="true" itemValue="idCourse" itemLabel="nameCourse" class="form-control input-sm"/>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="coursesTeacher" class ="help-inline" />
                    </div>
                </div>
            </div>
        </div>
    </form>

    <input type="hidden" name="idCourse" id="idCourseHidden"/>

    <table id="success" class="tg">
        <tr>
            <td width="180"><strong>Название курса</strong></td>
            <td width="120"><strong>Начало курса</strong></td>
            <td width="100"><strong>Конец курса</strong></td>
            <td width="90"><strong>Имя</strong></td>
            <td width="100"><strong>Фамилия</strong></td>
            <td width="60"><strong>Оценка</strong></td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#courseIdT').change(function(){
            var courseId = $(this).val();
            var url = "${pageContext.request.contextPath}/getListCourses";

            $.get(
                url,
                "courseId=" + courseId,
                function (userCourse) {
                    /* В случае неудачи мы получим результат с type равным error.
                    */
                    if (userCourse.type == 'error') {
                        /*
                         * ошибка в запросе
                         */
                        alert('error');
                        return(false);
                    }
                    else {
                        var html = '';
                        html +=
                            "<table class='tg'><tr>" +
                            "<th>Название курса</th>" +
                            "<th>Начало курса</th>" +
                            "<th>Конец курса</th>" +
                            "<th>Имя</th>" +
                            "<th>Фамилия</th>" +
                            "<th>Оценка</th></tr>";

                        $('#idCourseHidden').val(userCourse.idCourse);
                        for ( var i = 0; i < userCourse.length; i++ ) {
                            if(userCourse[i].markResult!=null){
                             html += "<tr><td>" + userCourse[i].nameCourse + "</td>" +
                                     "<td>" + userCourse[i].dateBeginCourse + "</td>" +
                                     "<td>" + userCourse[i].dateEndCourse + "</td>" +
                                     "<td>" + userCourse[i].firstName + "</td>" +
                                     "<td>" + userCourse[i].lastName + "</td>" +
                                     "<td>" + userCourse[i].markResult + "</td></tr>";}
                                     else {
                             html += "<tr><td>" + userCourse[i].nameCourse + "</td>" +
                                     "<td>" + userCourse[i].dateBeginCourse + "</td>" +
                                     "<td>" + userCourse[i].dateEndCourse + "</td>" +
                                     "<td>" + userCourse[i].firstName + "</td>" +
                                     "<td>" + userCourse[i].lastName + "</td>" +
                                     "<td>" + "оценка не выставлена" + "</td></tr>";}
                        }
                        html+="</table>";
                        $('#success').html(html);
                    }
                    console.log(userCourse);
                },
                "json"
            );
        });
    });
</script>
</body>
</html>
