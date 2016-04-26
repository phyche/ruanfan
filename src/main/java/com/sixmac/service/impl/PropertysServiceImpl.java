package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.dao.PropertysDao;
import com.sixmac.entity.Propertys;
import com.sixmac.service.PropertysService;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8 0008 上午 10:13.
 */
@Service
public class PropertysServiceImpl implements PropertysService {

    @Autowired
    private PropertysDao propertysDao;

    @Override
    public List<Propertys> findAll() {
        return propertysDao.findAll();
    }

    @Override
    public Page<Propertys> find(int pageNum, int pageSize) {
        return propertysDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Propertys> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Propertys getById(int id) {
        return propertysDao.findOne(id);
    }

    @Override
    public Propertys deleteById(int id) {
        Propertys propertys = getById(id);
        propertysDao.delete(propertys);
        return propertys;
    }

    @Override
    public Propertys create(Propertys propertys) {
        return propertysDao.save(propertys);
    }

    @Override
    public Propertys update(Propertys propertys) {
        return propertysDao.save(propertys);
    }

    @Override
    @Transactional
    public void deleteAll(int[] ids) {
        for (int id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page<Propertys> iPage(final String name, final String address, final Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Propertys> page = propertysDao.findAll(new Specification<Propertys>() {
            @Override
            public Predicate toPredicate(Root<Propertys> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (StringUtils.isNotEmpty(name)) {
                    Predicate pre = cb.like(root.get("name").as(String.class), "%" + name + "%");
                    predicateList.add(pre);
                }

                if (StringUtils.isNotEmpty(address)) {
                    Predicate pre = cb.like(root.get("address").as(String.class), "%" + address + "%");
                    predicateList.add(pre);
                }

                Predicate pre1 = cb.equal(root.get("parentId").as(Integer.class), 0);
                predicateList.add(pre1);

                Predicate pre2 = cb.notEqual(root.get("id").as(Integer.class), 0);
                predicateList.add(pre2);

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
    public List<Propertys> iPageByParentId(Integer parentId) {
        return propertysDao.iPageByParentId(parentId);
    }

    @Override
    public List<Propertys> pageByParentId(Integer parentId) {
        return null;
    }

    @Override
    public Page<Propertys> page(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Propertys> page = propertysDao.findAll(new Specification<Propertys>() {
            @Override
            public Predicate toPredicate(Root<Propertys> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                Predicate pre = cb.notEqual(root.get("id").as(Integer.class), 0);
                predicateList.add(pre);

                Predicate pre1 = cb.equal(root.get("parentId").as(Integer.class), 0);
                predicateList.add(pre1);

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
    public Page<Propertys> pageChild(final Integer parentId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Propertys> page = propertysDao.findAll(new Specification<Propertys>() {
            @Override
            public Predicate toPredicate(Root<Propertys> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                Predicate pre = cb.notEqual(root.get("id").as(Integer.class), 0);
                predicateList.add(pre);


                if (parentId != null) {
                    Predicate pre1 = cb.equal(root.get("parentId").as(Integer.class), parentId);
                    predicateList.add(pre1);
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
    public Page<Propertys> pageChild(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Propertys> page = propertysDao.findAll(new Specification<Propertys>() {
            @Override
            public Predicate toPredicate(Root<Propertys> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                Predicate pre = cb.notEqual(root.get("id").as(Integer.class), 0);
                predicateList.add(pre);

                Predicate pre1 = cb.notEqual(root.get("parentId").as(Integer.class), 0);
                predicateList.add(pre1);

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
}