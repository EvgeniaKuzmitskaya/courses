package by.myProject.model.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    private Long idRole;
    private String typeRole;

    public Role() {
    }

    public Role(Long idRole, String typeRole) {
        this.idRole = idRole;
        this.typeRole = typeRole;
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
