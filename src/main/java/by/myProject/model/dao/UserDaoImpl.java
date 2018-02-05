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
        User user = getSession().load(User.class, id);
        logger.info("User loaded successfully, User details=" + user);
        return user;
    }

    @Override
    public void save (User user) {
        getSession().save(user);
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
        String sql = "from User u where u.lastName = :lastname";
        Session session = super.getSession();
        Query query = session.createQuery(sql);
        query.setParameter("lastname", lastName);
        User user = (User) query.getSingleResult();
        logger.info("User by lastname::" + user);
        return user;

    }

    @Override
    public User findByUserName(String userName) {
        String sql = "from User u where u.userName = :username";
        Session session = super.getSession();
        Query query = session.createQuery(sql);
        query.setParameter("username", userName);
        User user = (User) query.uniqueResult();
        logger.info("User by username::" + user);
        return user;
    }


    @Override
    public List<User> findTeachers() {
        Session session = super.getSession();
        Query query = session.createQuery("select u from User u join u.roles p on p.typeRole = 'TEACHER'");
        List list = query.list();
        logger.info("Teacher list ::" + list);
        return list;
    }


    @Override
    public List<User> findStudents() {
        Session session = super.getSession();
        Query query = session.createQuery("select u from User u join u.roles p on p.typeRole = 'STUDENT'");
        List list = query.list();
        logger.info("Students list ::" + list);
        return list;
    }


}

