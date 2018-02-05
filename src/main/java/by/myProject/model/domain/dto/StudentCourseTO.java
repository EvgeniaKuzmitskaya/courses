package by.myProject.model.domain.dto;

import java.text.SimpleDateFormat;

public class StudentCourseTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long idUser;
    private Long idCourse;
    private String firstName;
    private String lastName;
    private String dateBeginCourse;
    private String dateEndCourse;
    private String nameCourse;

    private Integer markResult;

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getMarkResult() {
        return markResult;
    }

    public void setMarkResult(Integer markResult) {
        this.markResult = markResult;
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

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}
