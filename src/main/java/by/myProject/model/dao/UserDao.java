package by.myProject.model.dao;

import by.myProject.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao{

    User findById(Long id);
    User findByLastName(String lastName);
    User findByUserName(String userName);
    void save (User user);
    void deleteById(Long id);
    List<User> findAllUsers();
    void update (User user);
    Optional<User> findUser(String login, String password);
    List<User> findByRole();
    List<User> findByCourse();
    List<User> findStudents();
    List<User> findStudentsByCourse(String nameCourse);
    public List<User> findAllUsersRole();
    List<User> findTeacherByCourse(String courseName);

}
