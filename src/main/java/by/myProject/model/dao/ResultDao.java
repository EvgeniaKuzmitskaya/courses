package by.myProject.model.dao;

import by.myProject.model.domain.Result;
import by.myProject.model.domain.User;

import java.util.List;

public interface ResultDao {
    void save (Result result);
    void deleteById(Long id);
    List<Result> findAll();
    void update (Result result);
    Result findById(Long id);
    Result findByStudent(User user);
}
