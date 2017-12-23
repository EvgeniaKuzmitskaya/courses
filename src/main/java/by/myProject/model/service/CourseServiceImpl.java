package by.myProject.model.service;

import by.myProject.model.dao.CourseDao;
import by.myProject.model.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseDao courseDao;

    @Override
    public void save(Course course) {
        this.courseDao.save(course);
    }

    @Override
    public void deleteById(Long id) {
        this.courseDao.deleteById(id);
    }

    @Override
    public List<Course> findAll() {
        return this.courseDao.findAll();
    }

    @Override
    public void update(Course course) {
        this.courseDao.update(course);
    }

    @Override
    public Course findById(Long id) {
        return this.courseDao.findById(id);
    }

    @Override
    public Optional<Course> findCourse(String nameCours) {
        return this.courseDao.findCourse(nameCours);
    }
}
