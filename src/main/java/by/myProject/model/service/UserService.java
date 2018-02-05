package by.myProject.model.service;

import by.myProject.model.domain.User;
import by.myProject.model.domain.dto.UserTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Long id);
    User findByLastName(String lastName);
    Optional<User> findByUserName(String userName);
    void save (User user);
    void update (User user);
    void deleteById(Long id);
    List<User> findAllUsers();
    List<User> findTeachers();
    List<User> findStudents();
    List<UserTO> getAllStudents(Long courseId);
    List<UserTO> getAllUsers();
    Optional<User> saveUser(UserTO userTO, String roleId);
    UserTO getUserEdit(Long userId);
    Optional<User> updateUser(UserTO userTO, Long idRole, Long idUser);

}
