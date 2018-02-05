package by.myProject.model.dao;

import by.myProject.model.domain.Role;
import by.myProject.model.domain.Status;

import java.util.List;

public interface StatusDao {

    List<Status> findAll();
    Status findById(Long id);
}
