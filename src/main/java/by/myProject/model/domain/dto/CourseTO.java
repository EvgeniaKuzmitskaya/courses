package by.myProject.model.domain.dto;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;

public class CourseTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long idCourse;
    private String dateBeginCourse;
    private String dateEndCourse;
    private String nameCourse;
    private String descriptionCourse;
    private String userLastName;
    private String typeStatus;
    private String userId;
    private String statusId;
    private String markResult;
    private String idResult;


    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }


    public String getDateBeginCourse() {
        return dateBeginCourse;
    }

    public void setDateBeginCourse(String dateBeginCourse) {
        this.dateBeginCourse = dateBeginCourse;
    }


    public String getDateEndCourse() {
        return dateEndCourse;
    }

    public void setDateEndCourse(String dateEndCourse) {
        this.dateEndCourse = dateEndCourse;
    }

    @NotNull
    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getDescriptionCourse() {
        return descriptionCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }



    public String getIdResult() {
        return idResult;
    }

    public void setIdResult(String idResult) {
        this.idResult = idResult;
    }

    public String getMarkResult() {
        return markResult;
    }

    public void setMarkResult(String markResult) {
        this.markResult = markResult;
    }
}
