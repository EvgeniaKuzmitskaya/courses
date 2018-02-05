package by.myProject.model.dao;

import by.myProject.model.domain.UserCourse;

import java.util.List;
import java.util.Optional;

public interface UserCourseDao {
    UserCourse findById(Long id);
    Optional<UserCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);
    void save (UserCourse userCourse);
    void deleteById(Long id);
    void update (UserCourse userCourse);
    List<UserCourse> findAll();
}
