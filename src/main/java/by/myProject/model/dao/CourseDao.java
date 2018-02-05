package by.myProject.model.dao;

import by.myProject.model.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {

    Course findById(Long id);
    Optional<Course> findCourse(String nameCourse);
    void save (Course course);
    void deleteById(Long id);
    void update (Course course);
    List<Course> findAll();
}
