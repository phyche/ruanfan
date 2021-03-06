package com.sixmac.controller;

import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.GetImageInfo;
import com.sixmac.utils.QiNiuUploadImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/9 0009.
 */
@Controller
@RequestMapping(value = "common")
public class CommonsController {

    @Autowired
    private StylesService stylesService;

    @Autowired
    private AreasService areasService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DesignersService designersService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProducttypeService producttypeService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private VrtypeService vrtypeService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private MerchantsService merchantsService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private PackagesService packagesService;

    @Autowired
    private SpikesService spikesService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private AfflatusService afflatusService;

    /**
     * 权限列表
     *
     * @return
     */
    @RequestMapping("/roleList")
    @ResponseBody
    public List<Roles> roleList() {
        return rolesService.findAll();
    }

    /**
     * 发布人列表
     *
     * @return
     */
    @RequestMapping("/merchantList")
    @ResponseBody
    public List<Merchants> merchantList(Integer cityId) {
        if (null == cityId) {
            return merchantsService.findListWithSuccess();
        } else {
            return merchantsService.findListWithSuccess(cityId);
        }
    }

    /**
     * 品牌列表
     *
     * @return
     */
    @RequestMapping("/brandList")
    @ResponseBody
    public List<Brand> brandList() {
        return brandService.findAll();
    }

    /**
     * 商品分类列表
     *
     * @return
     */
    @RequestMapping("/sortList")
    @ResponseBody
    public List<Producttype> sortList() {
        return producttypeService.findAll();
    }

    /**
     * 省份列表
     *
     * @return
     */
    @RequestMapping("/provinceList")
    @ResponseBody
    public List<Province> provinceList() {
        return provinceService.findAll();
    }

    /**
     * 根据省份id查询城市列表
     *
     * @return
     */
    @RequestMapping("/cityList")
    @ResponseBody
    public List<City> cityList(Integer provinceId) {
        return cityService.findListByProvinceId(provinceId);
    }

    /**
     * 设计师列表
     *
     * @return
     */
    @RequestMapping("/designerList")
    @ResponseBody
    public List<Designers> designerList() {
        return designersService.findListWithSuccess();
    }

    /**
     * 风格列表
     *
     * @return
     */
    @RequestMapping("/styleList")
    @ResponseBody
    public List<Styles> styleList() {
        return stylesService.findAll();
    }

    /**
     * 区域列表
     *
     * @return
     */
    @RequestMapping("/areaList")
    @ResponseBody
    public List<Areas> areaList() {
        return areasService.findAll();
    }

    /**
     * 虚拟体验分类列表
     *
     * @return
     */
    @RequestMapping("/vrtypeList")
    @ResponseBody
    public List<Vrtype> typeList() {
        return vrtypeService.findAll();
    }

    /**
     * 商品列表
     *
     * @return
     */
    @RequestMapping("/productList")
    @ResponseBody
    public List<Products> productList(Integer merchantId) {
        if (null == merchantId) {
            return productsService.findListWithSuccess();
        } else {
            return productsService.findListWithSuccess(merchantId);
        }
    }

    /**
     * 灵感套图列表
     *
     * @return
     */
    @RequestMapping("/afflatusList")
    @ResponseBody
    public List<Afflatus> afflatusList() {
        return afflatusService.findListForVirtual();
    }

    /**
     * 根据商品id查询该商品的属性列表
     *
     * @param productId 商品id
     * @param type      类型，1=颜色，2=尺寸，3=材质
     * @return
     */
    @RequestMapping("/findInfoListByProductId")
    @ResponseBody
    public List<String> findColorListByProductId(Integer productId, Integer type) {
        Products products = productsService.getById(productId);
        List<String> list = new ArrayList<String>();
        String[] val = null;

        if (null != products) {
            switch (type) {
                case 1:
                    val = products.getColors().split(",");
                    break;
                case 2:
                    val = products.getSizes().split(" ");
                    break;
                case 3:
                    val = products.getMaterials().split(" ");
                    break;
            }
            for (String str : val) {
                list.add(str);
            }

            return list;
        }
        return null;
    }

    /**
     * 套餐列表
     *
     * @return
     */
    @RequestMapping("/packageList")
    @ResponseBody
    public List<Packages> packageList() {
        return packagesService.findAll();
    }

    /**
     * 秒杀列表
     *
     * @return
     */
    @RequestMapping("/spikeList")
    @ResponseBody
    public List<Spikes> spikeList() {
        return spikesService.findAll();
    }

    /**
     * 根据目标id和目标类型查询标签集合
     *
     * @param objectId
     * @param objectType
     * @return
     */
    @RequestMapping("/findLabelList")
    @ResponseBody
    public List<Label> findLabelList(Integer objectId, Integer objectType) {
        // 查询标签信息
        List<Label> list = labelService.findListByParams(objectId, objectType);
        if (null == list || list.size() == 0) {
            return null;
        } else {
            return list;
        }
    }

    /**
     * 根据商品id查询对应的封面图信息
     *
     * @return
     */
    @RequestMapping("/findCoverByProductId")
    @ResponseBody
    public Image findCoverByProductId(Integer productId) {
        return imageService.getById(productsService.getById(productId).getCoverId());
    }

    /**
     * 上传缓存图片
     *
     * @return
     */
    @RequestMapping("/addTempImage")
    @ResponseBody
    public Image addTempImage(ServletRequest request, MultipartRequest multipartRequest) {
        Image image = new Image();

        try {
            MultipartFile multipartFile = multipartRequest.getFile("tempImage");

            // 验证图片格式
            String originalFileName = multipartFile.getOriginalFilename().toLowerCase();
            String fileType = originalFileName.substring(originalFileName.indexOf("."));

            List<String> list = new ArrayList<String>();
            list.add(".jpg");
            list.add(".gif");
            list.add(".jpeg");
            list.add(".png");
            list.add(".bmp");

            if (!list.contains(fileType)) {
                return image;
            }

            String url = QiNiuUploadImgUtil.upload(multipartFile);
            image.setPath(url);

            imageService.create(image);

            // 使用线程更新图片的宽高信息
            GetImageInfo getImageInfo = new GetImageInfo();
            getImageInfo.setImage(image);
            getImageInfo.setKey(url);
            getImageInfo.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    /**
     * 根据产品id获取产品信息
     *
     * @param productId
     * @return
     */
    @RequestMapping("/getProductInfo")
    @ResponseBody
    public Products getProductInfo(Integer productId) {
        try {
            Products products = productsService.getById(productId);

            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据标签id删除标签
     *
     * @param labelId
     * @return
     */
    @RequestMapping("/deleteLabel")
    @ResponseBody
    public Integer deleteLabel(String labelId) {
        try {
            Label label = labelService.searchByLabelId(labelId);
            labelService.deleteById(label.getId());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 根据标签id查询标签最后停留的位置
     *
     * @param objectId
     * @param labelId
     * @return
     */
    @RequestMapping("/queryLastPosition")
    @ResponseBody
    public Label queryLastPosition(Integer objectId, String labelId) {
        try {
            Label label = labelService.findLastPosition(objectId, labelId);

            return label;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 保存标签信息
     *
     * @param objectId
     * @param labelId
     * @param name
     * @param objectType
     * @param height
     * @param width
     * @param leftPoint
     * @param topPoint
     * @param productId
     * @return
     */
    @RequestMapping("/saveLabelInfo")
    @ResponseBody
    public Integer saveLabelInfo(Integer objectId,
                                 String labelId,
                                 String name,
                                 Integer objectType,
                                 String height,
                                 String width,
                                 Double leftPoint,
                                 Double topPoint,
                                 Integer productId) {
        try {
            Label label = new Label();
            Label tempLabel = null;
            if (null != labelId && !labelId.equals("")) {
                tempLabel = labelService.findLastPosition(objectId, labelId);
                if (null != tempLabel) {
                    label = tempLabel;
                }
            }

            label.setName(name);
            label.setObjectId(objectId);
            label.setObjectType(objectType);
            label.setHeight(height);
            label.setWidth(width);
            label.setProduct(productsService.getById(productId));
            label.setLabelId(labelId);
            label.setLeftPoint(leftPoint);
            label.setTopPoint(topPoint);

            if (null == tempLabel) {
                labelService.create(label);
            } else {
                labelService.update(label);
            }

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
