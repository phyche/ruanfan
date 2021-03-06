package com.sixmac.controller.backend;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.entity.Brand;
import com.sixmac.service.BrandService;
import com.sixmac.service.OperatisService;
import com.sixmac.utils.QiNiuUploadImgUtil;
import com.sixmac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/15 0015.
 */
@Controller
@RequestMapping(value = "backend/brand")
public class BrandController extends CommonController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private OperatisService operatisService;

    @RequestMapping("index")
    public String index(ModelMap model) {
        return "backend/品牌分类";
    }

    @RequestMapping("/list")
    public void list(HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length) {
        if (null == start || start == 0) {
            start = 1;
        }
        int pageNum = getPageNum(start, length);
        Page<Brand> page = brandService.find(pageNum, length);

        // 循环查找每个品牌的关联套餐数量
        for (Brand brand : page.getContent()) {
            brand.setProductNum(brandService.findPackageListByBrandId(brand.getId()).size());
        }

        Map<String, Object> result = DataTableFactory.fitting(draw, page);
        WebUtil.printJson(response, result);
    }

    /**
     * 删除品牌分类
     *
     * @param brandId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(HttpServletRequest request, Integer brandId) {
        try {
            brandService.deleteById(request, brandId);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 新增品牌分类
     *
     * @param request
     * @param id
     * @param name
     * @param multipartRequest
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Integer save(HttpServletRequest request, Integer id, String name, MultipartRequest multipartRequest) {
        try {
            Brand brand = null;

            if (null != id) {
                brand = brandService.getById(id);
            } else {
                brand = new Brand();
            }

            brand.setName(name);
            brand.setUpdateTime(new Date());

            MultipartFile multipartFile = multipartRequest.getFile("mainImage");
            if (null != multipartFile) {
                String url = QiNiuUploadImgUtil.upload(multipartFile);
                brand.setCover(url);
            }

            if (null != id) {
                brandService.update(brand);
            } else {
                brandService.create(brand);
            }

            operatisService.addOperatisInfo(request, null == id ? "新增" : "修改" + "品牌分类 " + brand.getName());

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
