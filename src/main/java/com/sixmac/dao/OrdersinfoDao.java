package com.sixmac.dao;

import com.sixmac.entity.Ordersinfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4 0004 下午 2:45.
 */
public interface OrdersinfoDao extends JpaRepository<Ordersinfo, Integer>, JpaSpecificationExecutor<Ordersinfo> {

    @Query("select a from Ordersinfo a where a.order.id = ?1")
    public List<Ordersinfo> findListByOrderId(Integer orderId);

    @Query("select a from Ordersinfo a where a.productId = ?1 and a.star <> 0 and a.order.type = ?2 order by a.id desc")
    public List<Ordersinfo> findListBySourceId(Integer productId, Integer type);

    @Query("select a from Ordersinfo a where a.productId = ?1 and a.star <> 0 and a.order.type = ?2 order by a.id desc")
    public Page<Ordersinfo> pageBySourceId(Integer productId, Integer type, Pageable pageable);

    @Query("select a from Ordersinfo a where a.star <> 0 and a.order.type = 2 and a.productId in (select p.product.id from Packageproducts p where p.type = 1 and p.packages.id = ?1) order by a.id desc")
    public List<Ordersinfo> findListByPackageOrderId(Integer orderId);

    @Query("select a from Ordersinfo a where a.star <> 0 and a.order.type = 2 and a.productId in (select p.product.id from Packageproducts p where p.type = 1 and p.packages.id = ?1) order by a.id desc")
    public Page<Ordersinfo> pageByPackageOrderId(Integer orderId, Pageable pageable);
}