package by.myProject.model.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    private int idUser;
    private String userName;
    private String password;
    private String passwordConfirm;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles = new HashSet<Role>(0);
    private Set<Course> courses = new HashSet<Course>(0);
    private Set<Result> results = new HashSet<Result>(0);

    public User() {
    }

    public User(int idUser, String userName, String password, String firstName, String lastName, String email) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(int idUser, String userName, String password, String firstName, String lastName, String email, Set<Role> roles) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "login")
    @NotEmpty(message="Please Enter your login")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "firstname")
    @NotEmpty(message="Please Enter your name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    @NotEmpty(message="Please Enter your Surname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    @NotEmpty(message="Please Enter your email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password_confirm")
    @NotEmpty(message="Please Repeat password")
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id_role") })
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_course",
            joinColumns = { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id_course") })
    public Set<Course> getCourses() {
        return courses;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_result",
            joinColumns = { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id_result") })
    public Set<Result> getResults() {
        return results;
    }
    public void setResults(Set<Result> results) {
        this.results = results;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//
//        User user = (User) o;
//
//        if (idUser != user.idUser) return false;
//        if (!userName.equals(user.userName)) return false;
//        if (!password.equals(user.password)) return false;
//        if (!passwordConfirm.equals(user.passwordConfirm)) return false;
//        if (!firstName.equals(user.firstName)) return false;
//        if (!lastName.equals(user.lastName)) return false;
//        return email.equals(user.email);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idUser;
//        result = 31 * result + userName.hashCode();
//        result = 31 * result + password.hashCode();
//        result = 31 * result + passwordConfirm.hashCode();
//        result = 31 * result + firstName.hashCode();
//        result = 31 * result + lastName.hashCode();
//        result = 31 * result + email.hashCode();
//        return result;
//    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
