package by.myProject.model.dao;

import by.myProject.model.domain.Role;
import by.myProject.model.domain.Status;

import java.util.List;

public interface StatusDao {

    Status findByType(String typeStatus);
    List<Status> findAll();
    Status findById(Long id);
    List<Status> findAllByCourse(String nameCourse);
}
