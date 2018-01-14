package by.myProject.model.dao;

import by.myProject.model.domain.Role;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Long, Role> implements RoleDao{

    static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public Role findById(Long id) {
        Role role = (Role) getSession().load(Role.class, id);
        logger.info("Role loaded successfully, Role details=" + role);
        return role;
    }

    @Override
    public void save (Role role) {
        getSession().persist(role);
        logger.info("Role saved successfully, Role Details = " + role);
    }

    @Override
    public void deleteById(Long id) {
        Role role = findById(id);
        if(null != role){
            getSession().delete(role);
        }
        logger.info("Role deleted successfully, role details = " + role);
    }

    @Override
    public void update (Role role) {
        getSession().update(role);
        logger.info("Role updated successfully, Role Details = " + role);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAllRoles() {
        List<Role> roleList = getSession().createQuery("from Role ").list();
        for(Role role : roleList){
            logger.info("Role List::" + role);
        }
        return roleList;
    }

    @Override
    public Role findByType(String typeRole) {
        TypedQuery<Role> query = getSession().createQuery("FROM Role WHERE typeRole=?");
        query.setParameter(0,typeRole);
        return query.getSingleResult();
    }

}


