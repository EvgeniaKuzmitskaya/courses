package by.myProject.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_course")
public class UserCourse {
    private Long idResult;
    private Integer markResult;
    private User user;
    private Course course;

    public UserCourse() {
    }

    public UserCourse(Long idResult, Integer markResult) {
        this.idResult = idResult;
        this.markResult = markResult;
    }

    @Id
    @Column(name = "id_result")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }

    @Basic
    @Column(name = "mark_result")
    public Integer getMarkResult() {
        return markResult;
    }

    public void setMarkResult(Integer markResult) {
        this.markResult = markResult;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCourse)) return false;

        UserCourse userCourse = (UserCourse) o;

        if (idResult != userCourse.idResult) return false;
        if (markResult != userCourse.markResult) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idResult.hashCode();
        result = 31 * result + markResult;
        return result;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "idResult=" + idResult +
                ", markResult=" + markResult +
                '}';
    }
}
