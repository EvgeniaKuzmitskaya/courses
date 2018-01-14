package by.myProject.model.service;

import by.myProject.model.domain.Status;

import java.util.List;

public interface StatusService {

    Status findByType(String typeStatus);
    List<Status> findAll();
    Status findById(Long id);
    List<Status> findAllByCourse(String nameCourse);

}
