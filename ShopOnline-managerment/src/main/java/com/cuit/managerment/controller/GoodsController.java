package com.cuit.managerment.controller;

import com.cuit.common.result.BaseResult;
import com.cuit.common.result.FileResult;
import com.cuit.managerment.entity.Brand;
import com.cuit.managerment.entity.Goods;
import com.cuit.managerment.entity.GoodsCategory;
import com.cuit.managerment.entity.GoodsImages;
import com.cuit.managerment.service.*;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("goodsSelf")
public class GoodsController{
        @Autowired
        private GoodsCategoryService goodsCategoryService;
        @Autowired
        private BrandService brandService;
        @Autowired
        private GoodsService goodsService;
        @Autowired
        private GoodsImagesService goodsImagesService;
        @Autowired
        private UploadService uploadService;

        /**
         * 进入新增商品页面
         * @return
         */
        @RequestMapping("add")
        public String goodsAdd(Model model){
            //查询顶级分类
            List<GoodsCategory> gcList = goodsCategoryService.selectCategoryTopList();
            //查询所有品牌
            List<Brand> bList = brandService.selectAllBrands();
            model.addAttribute("gcList",gcList);
            model.addAttribute("bList",bList);

            return "goods/goods-add";
        }
        /**
         * 商品列表
         * @return
         */
        @RequestMapping("list")
        public String goodsList(Model model){
            System.out.println("进入商品列表了");

            //查询所有商品分类和商品品牌
            model.addAttribute("gcList",goodsCategoryService.selectCategoryList());
            model.addAttribute("brandList",brandService.selectAllBrands());
            return "goods/goods-list";
        }

    /**
     * 保存商品
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
        public BaseResult goodsSave(Goods goods){
            System.out.println(goods.getGoodsId());
            if(null != goods.getGoodsId()){

                return BaseResult.error();
            }
            goodsService.saveGoods(goods);
            BaseResult baseResult =  BaseResult.error();
            if(null != goods.getGoodsId()){
                baseResult = BaseResult.success();
                baseResult.setMessage(String.valueOf(goods.getGoodsId()));
            }else
                baseResult = BaseResult.error();
            return baseResult;
        }

    /**
     * 保存商品相册图片
     * @param file
     * @param goodsId
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("images/save")
    public BaseResult goodsImagesSave(MultipartFile file,Integer goodsId) throws IOException {

        String filename = file.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        filename = date +" "+ System.currentTimeMillis() + " "+filename;
        //.substring(filename.lastIndexOf("."))
        FileResult result = uploadService.upload(file.getInputStream(), filename);
        //上传图片成功
        if(!StringUtil.isEmpty(result.getFileUrl())){
            GoodsImages goodsImages = new GoodsImages();
            goodsImages.setImageUrl(result.getFileUrl());
            goodsImages.setGoodsId(goodsId);
            BaseResult baseResult = goodsImagesService.saveGoodsImages(goodsImages);
            return baseResult;
        }else
            return BaseResult.error();
    }

    /**
     * 商品列表-分页展示
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("listForPage")
    public BaseResult selectGoodsByPage(Goods goods,Integer pageNum,Integer pageSize){
        System.out.println(goods.getCatId());
        return goodsService.selectGoodsByPage(goods,pageNum,pageSize);
    }
    @ResponseBody
    @RequestMapping("delete/{id}")
    public BaseResult deleteGood(@PathVariable Integer id){
        if(null == id)
            return BaseResult.error();
        System.out.println(id);

        return goodsService.deleteGoodsById(id);
    }


}
