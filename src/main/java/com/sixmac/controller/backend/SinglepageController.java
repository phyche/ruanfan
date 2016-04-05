package com.sixmac.controller.backend;

import com.sixmac.common.DataTableFactory;
import com.sixmac.controller.common.CommonController;
import com.sixmac.entity.Singlepage;
import com.sixmac.service.SinglepageService;
import com.sixmac.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
@Controller
@RequestMapping(value = "backend/singlepage")
public class SinglepageController extends CommonController {

    @Autowired
    private SinglepageService singelpageservice;

    @RequestMapping(value = "index")
    public String index(ModelMap model) {
        return "backend/单页列表";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     String title,
                     String content,
                     Integer draw,
                     Integer start,
                     Integer length) {

        int pageNum = getPageNum(start, length);

        Page<Singlepage> page = singelpageservice.page(title, content, pageNum, length);
        Map<String, Object> result = DataTableFactory.fitting(draw, page);
        WebUtil.printJson(response, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Integer save(Integer id, String content) {

        try {
            Singlepage singlepage = singelpageservice.getById(id);

            singlepage.setCreateTime(new Date());
            singlepage.setContent(content);

            singelpageservice.update(singlepage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model, Integer id) {
        // 如果id不为空，则代表编辑
        if (null != id) {
            Singlepage singlepage = singelpageservice.getById(id);
            model.addAttribute("singlepage", singlepage);
        }
        return "backend/单页编辑";
    }
}
