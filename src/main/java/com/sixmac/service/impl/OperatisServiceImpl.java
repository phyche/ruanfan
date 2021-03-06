package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.OperatisDao;
import com.sixmac.dao.SysusersDao;
import com.sixmac.entity.Operatis;
import com.sixmac.entity.Sysusers;
import com.sixmac.service.OperatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/11 0011 下午 1:17.
 */
@Service
public class OperatisServiceImpl implements OperatisService {

    @Autowired
    private OperatisDao operatisDao;

    @Autowired
    private SysusersDao sysusersDao;

    @Override
    public List<Operatis> findAll() {
        return operatisDao.findAll();
    }

    @Override
    public Page<Operatis> find(int pageNum, int pageSize) {
        return operatisDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Operatis> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SIZE);
    }

    @Override
    public Operatis getById(int id) {
        return operatisDao.findOne(id);
    }

    @Override
    public Operatis deleteById(int id) {
        Operatis operatis = getById(id);
        operatisDao.delete(operatis);
        return operatis;
    }

    @Override
    public Operatis create(Operatis operatis) {
        return operatisDao.save(operatis);
    }

    @Override
    public Operatis update(Operatis operatis) {
        return operatisDao.save(operatis);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<Operatis> page(final String name, final String roleName, int pageNum, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Operatis> page = operatisDao.findAll(new Specification<Operatis>() {
            @Override
            public Predicate toPredicate(Root<Operatis> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (null != name) {
                    Predicate pre = cb.like(root.get("name").as(String.class), "%" + name + "%");
                    predicateList.add(pre);
                }
                if (roleName != null) {
                    Predicate pre = cb.like(root.get("roleName").as(String.class), "%" + roleName + "%");
                    predicateList.add(pre);
                }
                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }

    @Override
    public void addOperatisInfo(HttpServletRequest request, String description) {
        Integer loginId = (Integer) request.getSession().getAttribute(Constant.CURRENT_USER_ID);

        Sysusers sysusers = sysusersDao.findOne(loginId);

        Operatis operatis = new Operatis();
        operatis.setName(sysusers.getAccount());
        operatis.setRoleName(sysusers.getRole().getName());
        operatis.setDescription(description);
        operatis.setCreateTime(new Date());

        operatisDao.save(operatis);
    }
}