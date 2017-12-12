package by.myProject.model.dao;

import by.myProject.model.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findById(int id) {
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
    public void deleteById(int id) {
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
        List<User> userList = getSession().createQuery("from User").list();
        for(User user : userList){
            logger.info("User List::" + user);
        }
        return userList;

//        TypedQuery<User> query = getSession().createQuery("from User");
//        return query.getResultList();

        //        CriteriaQuery<User> query = createCriteriaBuilder().createQuery(User.class);
//        Root<User> root = query.from(User.class);
//        query.select(root);
//        query.orderBy(createCriteriaBuilder().asc(root.get("lastname")));
//        Query<User> q = getSession().createQuery(query);
//        List<User> users = q.getResultList();
//        return users;
    }

    @Override
    public User findByLastName(String lastName) {
        CriteriaQuery<User> query = createCriteriaBuilder().createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root.get("lastname"));
        Query<User> q = getSession().createQuery(query);
        User user = q.getSingleResult();
        return user;
    }

    @Override
    public User findByUserName(String userName) {
//        CriteriaQuery<User> query = createCriteriaBuilder().createQuery(User.class);
//        Root<User> root = query.from(User.class);
//        query.select(root.get("userName"));
//        Query<User> q=getSession().createQuery(query);
//        User user = q.getSingleResult();
//        return user;
        logger.info("UseName : {}", userName);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", userName));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getRoles());
        }
        return user;
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

}

