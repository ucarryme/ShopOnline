package com.cuit.managerment.controller;

import com.cuit.common.result.BaseResult;
import com.cuit.managerment.entity.GoodsCategory;
import com.cuit.managerment.service.GoodsCategoryService;
import com.cuit.managerment.vo.GoodsCategoryVo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("goods")
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    //页面跳转-商品分类列表管理界面
    @RequestMapping("category/list")
    public String categoryList(Model model){

        model.addAttribute("gcvList",goodsCategoryService.selectCategoryListForView());
        return "goods/category/category-list";
    }

    //页面跳转-新增商品
    @RequestMapping("category/add")
    public String categoryAdd(Model model) {
        List<GoodsCategory> gcList = goodsCategoryService.selectCategoryTopList();
        //将查询的所有顶级分类放置gcList中，以备前端使用
        model.addAttribute("gcList",gcList);
        return "goods/category/category-add";
    }
    /**
     * 商品分类-新增分类-联表查询
     * @param parentId
     * @return
     */
    @ResponseBody//声明返回数据非视图
    @GetMapping("category/{parentId}")
    public List<GoodsCategory> selectCategoryList(@PathVariable short parentId){
        System.out.println("父亲ID"+parentId);
        return goodsCategoryService.selectCategoryByParentId(parentId);
    }
    /**
     * 商品分类-新增分类-保存分类
     */
    @ResponseBody
    @RequestMapping("category/save")
    public BaseResult categorySave(GoodsCategory goodsCategory){
        int resultCol = goodsCategoryService.categorySave(goodsCategory);
        return resultCol>0?BaseResult.success():BaseResult.error();
    }
    /**
     * 商品分类-删除分类
     * 当待被删除分类的子分类为空时，返回id == -1的goodscategory 用于前端判断
     * 删除方式：首先给每个<tr>标签加上一个独一的id标签(gcvList+id)，再从删除方法del_fun(obj,url,id)传递参数，调用后台删除该分类，
     * 同时向前端返回子分类情况。判断待被删除分类的子分类是否为空为出口条件，通过递归调用del_fun(obj,url,id)删除前端<tr>标签
     *
     */
    @ResponseBody
    @RequestMapping("category/delete/{deleteId}")
    public List<GoodsCategory> categoryDelete(@PathVariable short deleteId){
        //创建当子分类为空时需返回的对象
        List<GoodsCategory> goodsCategories = new ArrayList<>();
        GoodsCategory goodsCategory3 = new GoodsCategory();
        goodsCategory3.setId((short)-1);
        goodsCategories.add(goodsCategory3);
        //查询预删除分类的信息
        GoodsCategory goodsCategory = goodsCategoryService.categorySelectOne(deleteId);
        //该分类不为空
        if(goodsCategory != null && null != goodsCategory.getId()){
            goodsCategoryService.categoryDelete(deleteId);
            //如果该分类为一级分类
            if(goodsCategory.getLevel() == 1){
                //查询该分类的二级分类列表
                List<GoodsCategory> list2 = goodsCategoryService.selectCategoryByParentId(deleteId);
                if(list2.isEmpty())
                    return goodsCategories;
                else
                    return list2;
                //如果该分类为二级分类
            }else if(goodsCategory.getLevel() == 2) {
                List<GoodsCategory> list3 = goodsCategoryService.selectCategoryByParentId(deleteId);
                    if(list3.isEmpty())
                        return goodsCategories;
                    else
                        return list3;
                //如果该分类为三级分类
            }else{
                 return goodsCategories;
            }
        }else {
            System.out.println(BaseResult.error());
            goodsCategory3.setId((short)-2);
            goodsCategories.add(goodsCategory3);
            return goodsCategories;
        }

    }
    @GetMapping("category/test")
    public String test(){
        goodsCategoryService.selectCategoryByParentId(Short.valueOf("12"));
        return "welcome";
    }

    /**
     * 修改分类列表的ishot
     * @param goodsCategory
     * @return
     */
    @ResponseBody
    @PostMapping("category/changeIshot")
    public BaseResult changeIshot(GoodsCategory goodsCategory){
        System.out.println(goodsCategory.getIsHot());
        return  goodsCategoryService.changeCategoryIshot(goodsCategory);
    }
    /**
     * 修改分类列表的isshow
     * @param goodsCategory
     * @return
     */
    @ResponseBody
    @PostMapping("category/changeIsshow")
    public BaseResult changeIsshow(GoodsCategory goodsCategory){
        System.out.println(goodsCategory.getIsShow());
        return  goodsCategoryService.changeCategoryIsshow(goodsCategory);
    }

    /**
     * 修改分类列表的catgroup
     * @param goodsCategory
     * @return
     */
    @ResponseBody
    @PostMapping("category/changeCatGroup")
    public BaseResult changeCatGroup(GoodsCategory goodsCategory){
        System.out.println(goodsCategory.getCatGroup());
        return  goodsCategoryService.changeCategouryCatgruop(goodsCategory);
    }
    /**
     * 修改分类列表的sortOrder
     * @param goodsCategory
     * @return
     */
    @ResponseBody
    @PostMapping("category/changeSortOrder")
    public BaseResult changeSortOrder(GoodsCategory goodsCategory){
        System.out.println(goodsCategory.getSortOrder());
        //return null;
        return  goodsCategoryService.changeCategorySortOrder(goodsCategory);
    }

    /**
     * 更改分类信息-查询分类
     * @param id
     * @param model
     * @return
     */
    @GetMapping("category/updateOneInfo/{id}")
    public String showInfo(@PathVariable short id,Model model){
        System.out.println(id);
        GoodsCategory goodsCategory = goodsCategoryService.selectCategoryById(id);
        List<GoodsCategory> goodsCategoryList = goodsCategoryService.selectCategoryTopList();
        if(goodsCategory != null && null != goodsCategoryList) {
            List<GoodsCategory> oneInfo = new ArrayList<>();
            oneInfo.add(goodsCategory);
            model.addAttribute("oneInfo",oneInfo);
            model.addAttribute("gcList",goodsCategoryList);
            return "goods/category/category-update";
        }else
            return "error";
    }
    @ResponseBody
    @RequestMapping("category/updateForm")
    public BaseResult updateForm(GoodsCategory goodsCategory){
        if (null == goodsCategory)
            return BaseResult.error();
        System.out.println(goodsCategory.getName());
        System.out.println(goodsCategory.getId());
        return goodsCategoryService.updateForm(goodsCategory);
    }

}
