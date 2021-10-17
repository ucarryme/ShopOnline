package com.cuit.portal.controller;


import com.cuit.portal.service.GoodsCategoryService;
import com.cuit.portal.vo.GoodsCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("goodsCategory")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 查询门户显示的商品分类
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public List<GoodsCategoryVo> categoryVoList(){

        System.out.println("进入分类列表了");return goodsCategoryService.selectCategoryListForView();
    }

}
