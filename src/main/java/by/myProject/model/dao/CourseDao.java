package by.myProject.model.dao;

import by.myProject.model.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {

    void save (Course course);
    void deleteById(Long id);
    List<Course> findAll();
    void update (Course course);
    Course findById(Long id);

    Optional<Course> findCourse(String nameCours);

}
