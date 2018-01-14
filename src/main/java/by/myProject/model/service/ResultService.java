package by.myProject.model.service;

import by.myProject.model.domain.Result;
import by.myProject.model.domain.User;

import java.util.List;

public interface ResultService {

    void save (Result result);
    void deleteById(Long id);
    List<Result> findAll();
    void update (Result result);
    Result findById(Long id);
    Result findByStudent(User user);

}
