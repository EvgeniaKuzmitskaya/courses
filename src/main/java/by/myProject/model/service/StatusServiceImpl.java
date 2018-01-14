package by.myProject.model.service;

import by.myProject.model.dao.StatusDao;
import by.myProject.model.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusDao statusDao;

    @Override
    public Status findByType(String typeStatus) {
        return this.statusDao.findByType(typeStatus);
    }

    @Override
    public List<Status> findAll() {
        return this.statusDao.findAll();
    }

    @Override
    @Transactional
    public Status findById(Long id) {
        return this.statusDao.findById(id);
    }

    @Override
    @Transactional
    public List<Status> findAllByCourse(String nameCourse) {

        return this.statusDao.findAllByCourse(nameCourse);
    }
}
