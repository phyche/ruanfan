package com.sixmac.service;

import com.sixmac.entity.Products;
import com.sixmac.entity.Styles;
import com.sixmac.service.common.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7 0007 下午 2:23.
 */
public interface StylesService extends ICommonService<Styles> {

    // 根据风格id查询对应的灵感集、商户和虚拟体验信息集合
    public Integer findListByStyleId(Integer styleId);
}