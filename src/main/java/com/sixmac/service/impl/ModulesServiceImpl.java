package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.ModulesDao;
import com.sixmac.entity.Modules;
import com.sixmac.service.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7 0007 下午 5:46.
 */
@Service
public class ModulesServiceImpl implements ModulesService {

    @Autowired
    private ModulesDao modulesDao;

    @Override
    public List<Modules> findAll() {
        return modulesDao.findAll();
    }

    @Override
    public Page<Modules> find(int pageNum, int pageSize) {
        return modulesDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Modules> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Modules getById(int id) {
        return modulesDao.findOne(id);
    }

    @Override
    public Modules deleteById(int id) {
        Modules modules = getById(id);
        modulesDao.delete(modules);
        return modules;
    }

    @Override
    public Modules create(Modules modules) {
        return modulesDao.save(modules);
    }

    @Override
    public Modules update(Modules modules) {
        return modulesDao.save(modules);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }
}