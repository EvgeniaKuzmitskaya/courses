package by.myProject.model.service;

import by.myProject.model.dao.UserDao;
import by.myProject.model.domain.Role;
import by.myProject.model.domain.User;
import by.myProject.model.domain.dto.UserTO;
import by.myProject.model.domain.enums.TypeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findByUserName(String userName) {

        return this.userDao.findByUserName(userName);
    }

    @Override
    public User findById(Long id) {

        return this.userDao.findById(id);
    }

    @Override
    public User findByLastName(String lastName) {
        return this.userDao.findByLastName(lastName);
    }

    @Override
    public void save(User user) {
        this.userDao.save(user);
    }

    @Override
    public void update(User user) {
        this.userDao.update(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userDao.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userDao.findAllUsers();
    }

    @Override
    public boolean isUserLoginUnique(Long id, String userName) {
        User user = findByUserName(userName);
        return ( user == null || ((id != null) && (user.getIdUser() == id)));
    }

    @Override
    public List<User> findByRole() {
        return this.userDao.findByRole();
    }

    @Override
    public List<User> findByCourse() {
        return this.userDao.findByCourse();
    }

    @Override
    public List<User> findStudents() {
        return this.userDao.findStudents();
    }

    @Override
    public List<User> findStudentsByCourse(String nameCourse) {
        return this.userDao.findStudentsByCourse(nameCourse);
    }

    @Override
    public Optional<User> findUser(String login, String password) {

        return this.userDao.findUser(login,password);
    }

    public List<User> findAllUsersRole(){
        return this.userDao.findAllUsersRole();
    }


    @Override
    @Transactional
    public List<UserTO> getAllUsers() {
        return findAllUsers().stream().map(user -> {
            UserTO dto = new UserTO();
            dto.setIdUser(user.getIdUser());
            dto.setUserName(user.getUserName());
            dto.setPassword(user.getPassword());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            user.getRoles().stream().findFirst().ifPresent(role ->
            dto.setTypeRole(role.getTypeRole()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<User> findTeacherByCourse(String courseName) {
        return this.userDao.findTeacherByCourse(courseName);
    }


}
