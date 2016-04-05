package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.RolesDao;
import com.sixmac.entity.Roles;
import com.sixmac.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/2 0002 21:37.
 */
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesDao rolesDao;

    @Override
    public List<Roles> findAll() {
        return rolesDao.findAll();
    }

    @Override
    public Page<Roles> find(int pageNum, int pageSize) {
        return rolesDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Roles> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Roles getById(int id) {
        return rolesDao.findOne(id);
    }

    @Override
    public Roles deleteById(int id) {
        Roles roles = getById(id);
        rolesDao.delete(roles);
        return roles;
    }

    @Override
    public Roles create(Roles roles) {
        return rolesDao.save(roles);
    }

    @Override
    public Roles update(Roles roles) {
        return rolesDao.save(roles);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<Roles> page(Integer pageNum, Integer pageSize) {
        return rolesDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }
}