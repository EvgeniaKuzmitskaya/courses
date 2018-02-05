package by.myProject.model.dao;

import by.myProject.model.domain.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository("courseDao")
public class CourseDaoImpl extends AbstractDao<Long, Course> implements CourseDao{

    static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);


    @Override
    public Course findById(Long id) {
        Course course = getSession().load(Course.class, id);
        logger.info("Course loaded successfully, Course details=" + course);
        return course;
    }

    @Override
    public Optional<Course> findCourse(String nameCourse) {
        Session session = super.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        Query<Course> q = getSession().createQuery(query);
        List<Course> courseList = q.getResultList();
        for (Course course : courseList) {
            logger.info("Course List::" + course);
            if (nameCourse.equals(course.getNameCourse())){
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        Course course = findById(id);
        if(null != course){
            getSession().delete(course);
        }
        logger.info("Course deleted successfully, Course details = " + course);
    }

    @Override
    public void save (Course course) {
        getSession().persist(course);
        logger.info("Course saved successfully, Course Details = " + course);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> findAll() {
        Session session = super.getSession();
        Query query = session.createQuery("select c from Course c");
        List list = query.getResultList();
        return list;
    }

    @Override
    public void update (Course course) {
        getSession().update(course);
        logger.info("Course updated successfully, Course Details = " + course);
    }
}
