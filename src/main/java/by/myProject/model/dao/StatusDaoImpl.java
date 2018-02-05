package by.myProject.model.dao;

import by.myProject.model.domain.Role;
import by.myProject.model.domain.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("statusDao")
public class StatusDaoImpl extends AbstractDao<Long, Role> implements StatusDao {

    static final Logger logger = LoggerFactory.getLogger(StatusDaoImpl.class);

    @Override
    public List<Status> findAll() {
        Session session = super.getSession();
        Query query = session.createQuery("select s from Status s");
        List list = query.list();
        logger.info("Status List::" + list);
        return list;
    }

    @Override
    public Status findById(Long id) {
        Status status = getSession().load(Status.class, id);
        logger.info("Status loaded successfully, Status details=" + status);
        return status;
    }

}
