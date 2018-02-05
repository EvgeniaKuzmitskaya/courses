package by.myProject.model.service;

import by.myProject.model.domain.Course;
import by.myProject.model.domain.UserCourse;
import by.myProject.model.domain.dto.CourseTO;
import by.myProject.model.domain.dto.StudentCourseTO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    Optional<Course> saveCourse (CourseTO courseTO);
    Course findById(Long id);
    Optional<Course> findCourse(String nameCourse);
    Optional<Course> saveStudent(CourseTO courseTO, String userName);
    CourseTO getAllCoursesStudent(Long courseId);
    void save (Course course);
    void deleteById(Long id);
    void update (Course course);
    List<Course> findAll();
    List<CourseTO> getAllCourses();
    SimpleDateFormat getDateFormat();
    List<CourseTO> getAllCoursesStudent(String userName);
    List<CourseTO> getAllCoursesTeacher(String userName);
    List<StudentCourseTO> getAllCourseStudents(Long courseId);
    CourseTO getCourseEdit(Long courseId);
    Optional<Course> updateCourse(CourseTO courseTO, Long statusId, Long idCourse);
}
