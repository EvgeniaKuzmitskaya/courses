package by.myProject.model.domain;

import by.myProject.model.dao.TypeRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    private Long idRole;
    private String typeRole;
    private Set<User> users = new HashSet<User>(0);




    public Role() {
    }

    public Role(Long idRole, String typeRole) {
        this.idRole = idRole;
        this.typeRole = typeRole;
    }

    public Role(Long idRole, String typeRole, Set<User> userSet) {
        this.idRole = idRole;
        this.typeRole = typeRole;
        this.users = users;
    }

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    @Column(name = "type_role")
    public String getTypeRole() {
        return typeRole;
    }

    public void setTypeRole(String typeRole) {
        this.typeRole = typeRole;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (!idRole.equals(role.idRole)) return false;
        return typeRole.equals(role.typeRole);
    }

    @Override
    public int hashCode() {
        int result = idRole.hashCode();
        result = 31 * result + typeRole.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", typeRole='" + typeRole + '\'' +
                '}';
    }
}
