package by.myProject.model.dao;

import by.myProject.model.domain.Role;
import by.myProject.model.domain.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao{

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findById(Long id) {
        User user = (User) getSession().load(User.class, id);
        logger.info("User loaded successfully, User details=" + user);
        return user;
    }

    @Override
    public void save (User user) {
        getSession().persist(user);
        logger.info("User saved successfully, User Details = " + user);
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        if(null != user){
            getSession().delete(user);
        }
        logger.info("User deleted successfully, user details = " + user);
    }

    @Override
    public void update (User user) {
        getSession().update(user);
        logger.info("User updated successfully, User Details = " + user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Session session = super.getSession();
        Query query = session.createQuery("select u from User u");
        List list = query.getResultList();
        for(Object user : list){
            logger.info("User List::" + user);
        }
        return list;

    }

    @Override
    public User findByLastName(String lastName) {
        Session session = getSession();
        Query query = session.createQuery("select u.lastName from User u WHERE u.lastName= :lastname");
        User user = (User) query.setParameter("lastname", lastName).getSingleResult();
        return user;
    }

        @Override
    public User findByUserName(String userName) {
//        logger.info("UseName : {}", userName);
//        Session session = super.getSession();
//        Query query = session.createQuery("select u from User u WHERE u.userName = :userName");
//        query.setParameter("userName", userName);
//        //        User user = new User();
////        if(!list.isEmpty()) {
////            user = (User) list.get(0);
////        }
//        return (User) query.getSingleResult();
            String sql = "from User u where u.userName = :username";

            Session session = super.getSession();

            Query query = session.createQuery(sql);
            query.setParameter("username", userName);

            return (User) query.uniqueResult();

    }

    @Override
    public Optional<User> findUser(String login, String password) {
        Session session = super.getSession();
        String sql = "from User as o where o.userName=? and o.password=?";
        TypedQuery<User> query = session.createQuery(sql);
        query.setParameter(0,login);
        query.setParameter(1,password);
        List<User> list = query.getResultList();
        if ((list != null) && (list.size() > 0)) {
            return Optional.of(list.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findByRole() {
        Session session = super.getSession();
        Query query = session.createQuery("select u from User u join u.roles p on p.typeRole = 'TEACHER'");
        List list = query.list();
        return list;
    }

    @Override
    public List<User> findByCourse() {
        Session session = super.getSession();
//        Query query = session.createQuery("select u, p from User u LEFT join u.courses p on p.nameCourse = :nameCourse");
        Query query = session.createQuery("select u, c from User u left join u.courses c");
//        Query query = session.createQuery("select u, c from User u JOIN u.courses c");

//        query.setParameter("nameCourse", course);
        List list = query.list();
        return list;
//        User user = (User) query.getSingleResult();
//        return user;

    }
    @Override
    public List<User> findStudents() {
        Session session = super.getSession();
        Query query = session.createQuery("select u from User u join u.roles p on p.typeRole = 'STUDENT'");
        List list = query.list();
        return list;
    }

    @Override
    public List<User> findStudentsByCourse(String nameCourse) {
        Session session = super.getSession();
        Query query = session.createQuery("select u from User u join u.roles p on p.typeRole = 'STUDENT' join  u.courses c on c.nameCourse = ?1");
        query.setParameter(1, "nameCourse");
        List list = query.list();
        return list;
    }

    public List<User> findAllUsersRole() {
        Session session = super.getSession();
        Query query = session.createQuery("select u, r from User u inner join u.roles r");
        List list = query.getResultList();
        for (Object user : list) {
            logger.info("User List::" + user);
        }
        return list;
    }

    @Override
    public List<User> findTeacherByCourse(String courseName) {
        Session session = super.getSession();
        Query query = session.createQuery("select u from User u join u.roles p on p.typeRole = 'TEACHER' join u.courses c on c.nameCourse = :nameCourse");
        query.setParameter("nameCourse", courseName);
        List list = query.list();
        return list;
    }





}

