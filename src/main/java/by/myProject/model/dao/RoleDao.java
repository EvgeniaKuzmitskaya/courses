package by.myProject.model.dao;

import by.myProject.model.domain.Role;

import java.util.List;

public interface RoleDao  {

    List<Role> findAllRoles();
    Role findByType(String typeRole);
    Role findById(Long id);
    void update(Role role);
    void save (Role role);
    void deleteById(Long id);


}
