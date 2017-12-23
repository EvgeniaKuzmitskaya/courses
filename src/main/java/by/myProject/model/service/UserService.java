package by.myProject.model.service;

import by.myProject.model.domain.User;

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
    public boolean isUserLoginUnique(Long id, String userName);
    List<User> findByRole();
}
