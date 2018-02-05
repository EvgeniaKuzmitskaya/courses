package by.myProject.model.dao;

import by.myProject.model.domain.User;

import java.util.List;

public interface UserDao{

    User findById(Long id);
    User findByLastName(String lastName);
    User findByUserName(String userName);
    void save (User user);
    void deleteById(Long id);
    void update (User user);
    List<User> findAllUsers();
    List<User> findTeachers();
    List<User> findStudents();

}
