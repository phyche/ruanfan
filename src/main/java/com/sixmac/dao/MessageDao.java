package com.sixmac.dao;

import com.sixmac.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4 0004 下午 2:43.
 */
public interface MessageDao extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message> {

    @Query("select a from Message a where a.types like ?1 or a.types like '%all%'")
    public List<Message> findListByType(String type);

    @Query("select a from Message a where a.types like ?1 or a.types like '%all%'")
    public Page<Message> pageByType(String type, Pageable pageable);
}