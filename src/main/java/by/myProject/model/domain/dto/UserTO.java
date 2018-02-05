package by.myProject.model.domain.dto;

import by.myProject.model.domain.Role;

import java.util.List;

public class UserTO {

    private Long idUser;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String typeRole;
    private Long idRole;

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeRole() {
        return typeRole;
    }

    public void setTypeRole(String typeRole) {
        this.typeRole = typeRole;
    }


}
