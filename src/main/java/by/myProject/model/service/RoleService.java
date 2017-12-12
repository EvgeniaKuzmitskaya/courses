package by.myProject.model.service;

import by.myProject.model.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();
    Role findByType(String typeRole);
    Role findById(int id);
    void update(Role role);
    void save (Role role);
    void deleteById(int id);



}

