package by.myProject.model.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {

    private Long idStatus;
    private String typeStatus;
    private Set<Course> courses = new HashSet<>();

    public Status() {
    }

    public Status(Long idStatus, String typeStatus) {
        this.idStatus = idStatus;
        this.typeStatus = typeStatus;
    }

    @Id
    @Column(name = "id_status")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    @Column(name = "type_status")
    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }



}
