package by.myProject.model.service;

import by.myProject.model.dao.UserDao;
import by.myProject.model.domain.Course;
import by.myProject.model.domain.Role;
import by.myProject.model.domain.User;
import by.myProject.model.domain.UserCourse;
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

    @Autowired
    CourseService courseService;

    @Autowired
    RoleService roleService;

    @Override
    public User findById(Long id) {
        return this.userDao.findById(id);
    }

    @Override
    public User findByLastName(String lastName) {
        return this.userDao.findByLastName(lastName);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        User user = this.userDao.findByUserName(userName);
        if (user != null){
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
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
    public List<User> findTeachers() {
        return this.userDao.findTeachers();
    }

    @Override
    public List<User> findStudents() {
        return this.userDao.findStudents();
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
    @Transactional
    public List<UserTO> getAllStudents(Long courseId) {
        Course course = this.courseService.findById(courseId);
        return course.getUserCourses().stream()
                .filter(userCourse -> isStudent(userCourse.getUser()))
                .map(userCourse -> {
            UserTO dto = new UserTO();
            dto.setIdUser(userCourse.getUser().getIdUser());
            dto.setUserName(userCourse.getUser().getUserName());
            dto.setPassword(userCourse.getUser().getPassword());
            dto.setFirstName(userCourse.getUser().getFirstName());
            dto.setLastName(userCourse.getUser().getLastName());
            dto.setEmail(userCourse.getUser().getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    // является ли user студентом
    private boolean isStudent(User user) {
        for (Role role: user.getRoles()){
            if (TypeRole.ROLE_STUDENT.getRoleType().equals(role.getTypeRole())){
                return true;
            }
        }
        return false;
    }

    // save user
    @Override
    @Transactional
    public Optional<User> saveUser(UserTO userTO, String roleId) {
        User user = new User();
        user.setIdUser(userTO.getIdUser());
        user.setUserName(userTO.getUserName());
        user.setPassword(userTO.getPassword());
        user.setFirstName(userTO.getFirstName());
        user.setLastName(userTO.getLastName());
        user.setEmail(userTO.getEmail());
        Role r = roleService.findById(Long.parseLong(roleId));
        user.getRoles().add(r);
        this.save(user);
        return this.findByUserName(user.getUserName());
    }


    // use for edit form
    @Override
    @Transactional
    public UserTO getUserEdit(Long userId) {
            User user = this.findById(userId);
            UserTO dto = new UserTO();
            dto.setIdUser(user.getIdUser());
            dto.setUserName(user.getUserName());
            dto.setPassword(user.getPassword());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            user.getRoles().stream().findFirst().ifPresent(role ->
                    dto.setTypeRole(role.getTypeRole())
            );
            dto.setIdRole(user.getIdUser());
            return dto;
       }


    // update user
    @Override
    @Transactional
    public Optional<User> updateUser(UserTO userTO, Long idRole, Long idUser) {
        User user = this.findById(userTO.getIdUser());
        user.setUserName(userTO.getUserName());
        user.setPassword(userTO.getPassword());
        user.setFirstName(userTO.getFirstName());
        user.setLastName(userTO.getLastName());
        user.setEmail(userTO.getEmail());
        Role r = roleService.findById(Long.parseLong(userTO.getTypeRole()));
        user.getRoles().clear();
        user.getRoles().add(r);
        this.update(user);
        return this.findByUserName(user.getUserName());
    }
}
