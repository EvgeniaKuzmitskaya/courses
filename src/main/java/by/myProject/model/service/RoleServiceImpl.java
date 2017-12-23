package by.myProject.model.service;

import by.myProject.model.dao.RoleDao;
import by.myProject.model.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao roleDao;

    @Override
    public Role findById(Long id) {
        return this.roleDao.findById(id);
    }

    @Override
    public Role findByType(String typeRole) {
        Role role = this.roleDao.findByType(typeRole);
        return role;
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleDao.findAllRoles();
    }

    @Override
    public void save(Role role) {
        this.roleDao.save(role);
    }

    @Override
    public void deleteById(Long id) {
        this.roleDao.deleteById(id);
    }

    @Override
    public void update(Role role) {
        this.roleDao.update(role);
    }
}

