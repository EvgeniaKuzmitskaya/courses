package by.myProject.model.dao;

import by.myProject.model.domain.Result;

import java.util.List;

public interface ResultDao {
    void save (Result result);
    void deleteById(int id);
    List<Result> findAll();
    void update (Result result);
    Result findById(int id);
}
