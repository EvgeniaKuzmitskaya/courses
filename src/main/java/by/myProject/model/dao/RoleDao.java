package by.myProject.model.dao;

import by.myProject.model.domain.Role;

import java.util.List;

public interface RoleDao  {

    Role findById(Long id);
    void update(Role role);
    void save (Role role);
    void deleteById(Long id);
    List<Role> findAllRoles();

}
