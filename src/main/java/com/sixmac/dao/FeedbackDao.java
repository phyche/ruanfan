package com.sixmac.dao;

import com.sixmac.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/3/4 0004 下午 2:42.
 */
public interface FeedbackDao extends JpaRepository<Feedback, Integer>, JpaSpecificationExecutor<Feedback> {

}