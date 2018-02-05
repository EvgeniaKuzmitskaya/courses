package by.myProject.model.dao;

import by.myProject.model.domain.UserCourse;
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

@Repository("resultDao")
public class UserCourseDaoImpl extends AbstractDao<Long, UserCourse> implements UserCourseDao {

    static final Logger logger = LoggerFactory.getLogger(UserCourseDaoImpl.class);

    @Override
    public void deleteById(Long id) {
        UserCourse userCourse = findById(id);
        if(null != userCourse){
            getSession().delete(userCourse);
        }
        logger.info("UserCourse deleted successfully, UserCourse details = " + userCourse);
    }

    @Override
    public List<UserCourse> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<UserCourse> query = builder.createQuery(UserCourse.class);
        Root<UserCourse> root = query.from(UserCourse.class);
        query.select(root);
        Query<UserCourse> q = getSession().createQuery(query);
        List<UserCourse> userCourseList = q.getResultList();
        for (UserCourse userCourse : userCourseList) {
            logger.info("UserCourse List::" + userCourse);
        }
        return userCourseList;
    }

    @Override
    public UserCourse findById(Long id) {
        UserCourse userCourse = getSession().load(UserCourse.class, id);
        logger.info("UserCourse loaded successfully, UserCourse details=" + userCourse);
        return userCourse;
    }

    @Override
    public void update (UserCourse userCourse) {
        getSession().update(userCourse);
        logger.info("UserCourse updated successfully, UserCourse Details = " + userCourse);
    }

    @Override
    public Optional<UserCourse> findByStudentIdAndCourseId(Long studentId, Long courseId) {
        String sql = "from UserCourse uc where uc.user.idUser = :studentId " +
                "and uc.course.idCourse = :courseId";
        Session session = super.getSession();
        Query<UserCourse> query = session.createQuery(sql);
        query.setParameter("studentId", studentId);
        query.setParameter("courseId", courseId);
        return query.getResultList().stream().findAny();
    }
}
