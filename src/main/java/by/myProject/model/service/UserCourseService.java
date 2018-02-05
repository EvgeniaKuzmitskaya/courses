package by.myProject.model.service;

import by.myProject.model.domain.UserCourse;
import by.myProject.model.domain.dto.StudentCourseTO;

import java.util.List;
import java.util.Optional;

public interface UserCourseService {

    UserCourse findById(Long id);
    Optional<UserCourse> saveResult(StudentCourseTO studentCourseTO);
    void save (UserCourse userCourse);
    void deleteById(Long id);
    void update (UserCourse userCourse);
    List<UserCourse> findAll();
}
