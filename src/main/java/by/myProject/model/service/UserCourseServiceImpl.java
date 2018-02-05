package by.myProject.model.service;

import by.myProject.model.dao.UserCourseDao;
import by.myProject.model.dao.UserDao;
import by.myProject.model.domain.Course;
import by.myProject.model.domain.User;
import by.myProject.model.domain.UserCourse;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.domain.dto.StudentCourseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userCourseService")
@Transactional
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    UserCourseDao userCourseDao;

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Override
    public void save(UserCourse userCourse) {
        this.userCourseDao.save(userCourse);
    }

    @Override
    public void deleteById(Long id) {
        this.userCourseDao.deleteById(id);
    }

    @Override
    public List<UserCourse> findAll() {
        return this.userCourseDao.findAll();
    }

    @Override
    public void update(UserCourse userCourse) {
        this.userCourseDao.update(userCourse);
    }

    @Override
    public UserCourse findById(Long id) {
        return  this.userCourseDao.findById(id);
    }

    // сохраняет оценку
    @Override
    @Transactional
    public Optional<UserCourse> saveResult(StudentCourseTO studentCourseTO) {
        Optional<UserCourse> maybeUserCourse = userCourseDao.findByStudentIdAndCourseId(
                studentCourseTO.getIdUser(), studentCourseTO.getIdCourse());
        UserCourse userCourse;
        if (maybeUserCourse.isPresent()){
            userCourse = maybeUserCourse.get();
            userCourse.setMarkResult(studentCourseTO.getMarkResult());
            userCourseDao.update(userCourse);
            return Optional.of(userCourseDao.findById(userCourse.getIdResult()));
        } else {
            userCourse = new UserCourse();
            Course course = courseService.findById(studentCourseTO.getIdCourse());
            User user = userService.findById(studentCourseTO.getIdUser());
            userCourse.setUser(user);
            userCourse.setCourse(course);
            userCourse.setMarkResult(studentCourseTO.getMarkResult());
            userCourseDao.save(userCourse);
            return userCourseDao.findByStudentIdAndCourseId(
                    studentCourseTO.getIdUser(), studentCourseTO.getIdCourse());
        }
    }
}
