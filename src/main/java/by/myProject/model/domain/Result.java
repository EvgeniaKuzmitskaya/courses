package by.myProject.model.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Result {
    private int idResult;
    private int markResult;
    private Set<User> users = new HashSet<User>(0);


    @Id
    @Column(name = "id_result")
    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    @Basic
    @Column(name = "mark_result")
    public int getMarkResult() {
        return markResult;
    }

    public void setMarkResult(int markResult) {
        this.markResult = markResult;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "results")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result result = (Result) o;

        if (idResult != result.idResult) return false;
        if (markResult != result.markResult) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idResult;
        result = 31 * result + markResult;
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "idResult=" + idResult +
                ", markResult=" + markResult +
                '}';
    }
}
