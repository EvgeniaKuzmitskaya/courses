package by.myProject.model.service;

import by.myProject.model.domain.Course;
import by.myProject.model.domain.dto.CourseTO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    void save (Course course);
    Optional<Course> save (CourseTO dto);
    void deleteById(Long id);
    List<Course> findAll();
    void update (Course course);
    Course findById(Long id);
    Optional<Course> findCourse(String nameCourse);
    List<Course> findAllByUser();
    List<CourseTO> getAllCourses();
    SimpleDateFormat getDateFormat();
    CourseTO getAllCoursesStudent(Long courseId);
//    Optional<Course> FindFormStudent(Course course);
    Optional<Course> saveStudent(CourseTO courseTO, String userName);
    List<CourseTO> getAllCoursesStudent(String userName);
    List<Course> findAllStudentCourse(String userName);


}
