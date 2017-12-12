package by.myProject.model.service;

import by.myProject.model.dao.UserDao;
import by.myProject.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public User findById(int id) {
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
    public void deleteById(int id) {
        this.userDao.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userDao.findAllUsers();
    }

    @Override
    public boolean isUserLoginUnique(Integer id, String userName) {
        User user = findByUserName(userName);
        return ( user == null || ((id != null) && (user.getIdUser() == id)));
    }

    @Override
    public Optional<User> findUser(String login, String password) {
       return this.userDao.findUser(login,password);
    }
}
