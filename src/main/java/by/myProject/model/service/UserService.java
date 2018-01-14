package by.myProject.model.service;

import by.myProject.model.domain.User;
import by.myProject.model.domain.dto.UserTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Long id);
    User findByLastName(String lastName);
    User findByUserName(String userName);
    void save (User user);
    void deleteById(Long id);
    List<User> findAllUsers();
    void update (User user);
    Optional<User> findUser(String login, String password);
    boolean isUserLoginUnique(Long id, String userName);
    List<User> findByRole();
    List<User> findByCourse();
    List<User> findStudents();
    List<User> findStudentsByCourse(String nameCourse);
    List<User> findAllUsersRole();
    List<UserTO> getAllUsers();
    List<User> findTeacherByCourse(String courseName);


}
