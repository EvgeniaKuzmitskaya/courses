package by.myProject.model.domain.dto;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class CourseTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long idCourse;
    private String dateBeginCourse;
    private String dateEndCourse;
    private String nameCourse;
    private String statusCourse;
    private String descriptionCourse;

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

    public String getStatusCourse() {
        return statusCourse;
    }

    public void setStatusCourse(String statusCourse) {
        this.statusCourse = statusCourse;
    }

    public String getDescriptionCourse() {
        return descriptionCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }
}
