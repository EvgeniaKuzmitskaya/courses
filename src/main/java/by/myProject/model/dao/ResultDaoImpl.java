package by.myProject.model.dao;

import by.myProject.model.domain.Result;
import by.myProject.model.domain.User;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("resultDao")
public class ResultDaoImpl extends AbstractDao<Long, Result> implements ResultDao{

    static final Logger logger = LoggerFactory.getLogger(ResultDaoImpl.class);

    @Override
    public void deleteById(Long id) {
        Result result = findById(id);
        if(null != result){
            getSession().delete(result);
        }
        logger.info("Result deleted successfully, Result details = " + result);
    }

    @Override
    public List<Result> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Result> query = builder.createQuery(Result.class);
        Root<Result> root = query.from(Result.class);
        query.select(root);
        Query<Result> q = getSession().createQuery(query);
        List<Result> resultList = q.getResultList();
        for (Result result : resultList) {
            logger.info("Result List::" + result);
        }
        return resultList;
    }

    @Override
    public Result findById(Long id) {
        Result result = (Result) getSession().load(Result.class, id);
        logger.info("Result loaded successfully, Result details=" + result);
        return result;
    }

    @Override
    public void update (Result result) {
        getSession().update(result);
        logger.info("Result updated successfully, Result Details = " + result);
    }

    @Override
    public Result findByStudent(User user) {
        TypedQuery<Result> query = getSession().createQuery("SELECT r.markResult FROM Result r join User u WHERE u.userName=?");
        query.setParameter(0,user);
        return query.getSingleResult();
    }


}
