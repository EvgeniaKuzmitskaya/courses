package by.myProject.model.service;

import by.myProject.model.dao.ResultDao;
import by.myProject.model.domain.Result;
import by.myProject.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("resultService")
@Transactional
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultDao resultDao;

    @Override
    public void save(Result result) {
        this.resultDao.save(result);
    }

    @Override
    public void deleteById(Long id) {
        this.resultDao.deleteById(id);
    }

    @Override
    public List<Result> findAll() {
        return this.resultDao.findAll();
    }

    @Override
    public void update(Result result) {
        this.resultDao.update(result);
    }

    @Override
    public Result findById(Long id) {
        return  this.resultDao.findById(id);
    }

    @Override
    public Result findByStudent(User user) {
        return this.resultDao.findByStudent(user);
    }






}
