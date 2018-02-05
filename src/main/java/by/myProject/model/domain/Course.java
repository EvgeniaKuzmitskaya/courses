package by.myProject.model.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    private Long idCourse;
    private String nameCourse;
    private Date dateBeginCourse;
    private Date dateEndCourse;
    private String descriptionCourse;
    private Status status;
    private List<UserCourse> userCourses = new ArrayList<>();

   public Course(){}

    public Course(Long idCourse, String nameCourse, Date dateBeginCourse, Date dateEndCourse, String descriptionCourse) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.dateBeginCourse = dateBeginCourse;
        this.dateEndCourse = dateEndCourse;
        this.descriptionCourse = descriptionCourse;
    }

    @Id
    @Column(name = "id_course")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    @Basic
    @Column(name = "name_course")
    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_begin_course")
    @DateTimeFormat(pattern="dd.MM.yyyy")
    public Date getDateBeginCourse() {
        return dateBeginCourse;
    }

    public void setDateBeginCourse(Date dateBeginCourse) {
        this.dateBeginCourse = dateBeginCourse;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end_course")
    @DateTimeFormat(pattern="dd.MM.yyyy")
    public Date getDateEndCourse() {
        return dateEndCourse;
    }

    public void setDateEndCourse(Date dateEndCourse) {
        this.dateEndCourse = dateEndCourse;
    }

    @Basic
    @Column(name = "description_course")
    public String getDescriptionCourse() {
        return descriptionCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    public List<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (idCourse != course.idCourse) return false;
        if (nameCourse != null ? !nameCourse.equals(course.nameCourse) : course.nameCourse != null) return false;
        if (dateBeginCourse != null ? !dateBeginCourse.equals(course.dateBeginCourse) : course.dateBeginCourse != null)
            return false;
        if (dateEndCourse != null ? !dateEndCourse.equals(course.dateEndCourse) : course.dateEndCourse != null)
            return false;
        if (descriptionCourse != null ? !descriptionCourse.equals(course.descriptionCourse) : course.descriptionCourse != null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", nameCourse='" + nameCourse + '\'' +
                ", dateBeginCourse=" + dateBeginCourse +
                ", dateEndCourse=" + dateEndCourse +
                ", descriptionCourse='" + descriptionCourse + '\'' +
                '}';
    }
}
