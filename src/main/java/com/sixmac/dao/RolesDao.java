package com.sixmac.dao;

import com.sixmac.entity.Reserve;
import com.sixmac.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/4 0004 下午 2:47.
 */
public interface RolesDao extends JpaRepository<Roles, Integer>,JpaSpecificationExecutor<Reserve> {

}