<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <meta charset="UTF-8">
</head>
<body>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/app.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript" ></script>
<div class="generic-container">

    <table>
        <tr>
            <td class="title lead">
                Put the rating |
                <a href="${contextPath}/teacherPage">Teacher page</a>
            </td>
        </tr>
    </table>
    <br/>

<form id="resultForm"  class="form-horizontal" action="/putResult" modelAttribute="userCourse" method="post">

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Название курса:</label>
            <div class="col-md-7">
                <form:select id="courseIdT" name = "idCourse" path="coursesTeacher" class="form-control input-sm">
                    <form:option value="0" label="select course"></form:option>
                    <form:options items="${coursesTeacher}" multiple="true" itemValue="idCourse" itemLabel="nameCourse" class="form-control input-sm"/>
                </form:select>
                <div class="has-error">
                    <form:errors path="coursesTeacher" class ="help-inline" />
                </div>
            </div>
        </div>
    </div>

    <input type="hidden" name="idCourse" id="idCourseHidden"/>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Студент: </label>
            <div class="col-md-7 name-student">
                <form:select id="studentId" name="idUser" path="students" class="form-control input-sm">
                    <form:option value="0" label="select student"></form:option>
                    <form:options items="${students}" multiple="true" itemValue="idUser" itemLabel="lastName" class="form-control input-sm"/>
                </form:select>
                <div class="has-error">
                    <form:errors path="students" class ="help-inline" />
                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-3 control-lable">Оценка</label>
            <div class="col-md-7">
                <form:select id="markId" name = "markResult" path="mark" class="form-control input-sm">
                    <option value="0">-select mark-</option>
                    <option value="2">3</option>
                    <option value="3">4</option>
                    <option value="4">5</option>
                    <option value="5">6</option>
                    <option value="6">7</option>
                    <option value="7">8</option>
                    <option value="8">9</option>
                    <option value="9">10</option>
                </form:select>
                <div class="has-error">
                    <form:errors path="mark" class ="help-inline" />
                </div>
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

<script type="text/javascript">
    $(document).ready(function () {
        $('#courseIdT').change(function(){
            var courseId = $(this).val();
            if (courseId == '0') {
                $('#studentId').html('<option>select student</option>');
                $('#studentId').attr('disabled', true);
                return (false);
            }
            $('#studentId').attr('disabled', true);
            $('#studentId').html('<option>загрузка...</option>');
            var url = "${pageContext.request.contextPath}/getTeacherCourses";

            /*
             * GET'овый AJAX запрос
             * подробнее о синтаксисе читайте
             * на сайте http://docs.jquery.com/Ajax/jQuery.get
             * Данные будем кодировать с помощью JSON
             */
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
//                    $('.name-student').html("<span>" + userCourse.userLastName + "</span>");
//                        1 вариант
//                        var options = '';
//                        $(userCourse.students).each(function() {
//                            /*
//                             * и добавляем в селект по студенту
//                             */
//                            options +='<option value="' + $(this).attr(userCourse.userId) + '">'+ $(this).attr(userCourse.userLastName) + '</option>';
////                            options +='<option>'+ $(this).attr(userCourse.userLastName) + '</option>';
//                        });
////                        $('#studentId').html(options);
//                        $('#studentId').html('<option value="0">-select student-</option>'+options);
//                        $('#studentId').attr('disabled', false);
//                        $('#idCourseHidden').val(userCourse.idCourse);
                        //тут неплохо бы проверку что если список пустой...
//
                        for ( var i = 0; i < userCourse.length; i++ ) {
                            // Каждое полученное значение вставим в список
                            $( '#studentId' ).append( '<option value="' + userCourse[i].idUser + '">' + userCourse[i].lastName + '</option>' );
                            //ставим оценку в нижний селект если она уже есть
                            if (userCourse.markResult!= undefined && userCourse.markResult!=null){
                                $('#markId').append('<option value="' + userCourse[i].idResult + '">' + userCourse[i].markResult + '</option>')
                            }
                        }

                        // После того, как мы сформировали список, мы можем сделать его активным
                        // обратившись к его свойству disabled
//                        $('#studentId').html('<option value="0">-select student-</option>'+options);
                        $('#studentId').prop( 'disabled', false ); // Включаем поле
                        $('#idCourseHidden').val(userCourse.idCourse);
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
