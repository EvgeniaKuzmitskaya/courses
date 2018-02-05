package by.myProject.model.service;

import by.myProject.model.domain.Status;

import java.util.List;

public interface StatusService {

    List<Status> findAll();
    Status findById(Long id);
}
