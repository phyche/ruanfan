package com.sixmac.service;

import com.sixmac.entity.Report;
import com.sixmac.service.common.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/3/29 0029.
 */
public interface ReportService extends ICommonService<Report> {

    public Page<Report> page(Integer userId, Integer sourceId, Integer type, int pageNum, int pageSize);
}