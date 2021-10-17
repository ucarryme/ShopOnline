package com.cuit.managerment.service;

import com.cuit.common.result.BaseResult;
import com.cuit.managerment.entity.Goods;
import com.cuit.managerment.entity.GoodsCategory;
import com.cuit.managerment.vo.GoodsCategoryVo;

import java.util.List;

public interface GoodsCategoryService {
    /**
     * 商品分类-新增商品-查询顶级分类
     *
     * @return
     */
    List<GoodsCategory> selectCategoryTopList();

    /**
     * 商品分类-新增分类-级联查询
     * @param parentId
     * @return
     */
    List<GoodsCategory>selectCategoryByParentId(Short parentId);

    /**
     * 新增分类
     * @param goodsCategory
     * @return
     */
    int categorySave(GoodsCategory goodsCategory);

    /**
     * 商品分类-列表
     * @return
     */
    List<GoodsCategoryVo> selectCategoryListForView();
    /**
     * 查询单个分类
     * @param id
     * @return
     */
    GoodsCategory categorySelectOne(Short id);
    /**
     * 删除分类
     * @param id
     * @return
     */
    int categoryDelete(Short id);

    /**
     * 查询分类列表-商品列表使用
     * @return
     */
    List<GoodsCategory> selectCategoryList();

    /**
     * 改变分类的推荐
     * @param goodsCategory
     * @return
     */
    BaseResult changeCategoryIshot(GoodsCategory goodsCategory);
    /**
     * 改变分类的展示
     * @param goodsCategory
     * @return
     */
    BaseResult changeCategoryIsshow(GoodsCategory goodsCategory);

    /**
     * 改变分类的分组
     * @param goodsCategory
     * @return
     */
    BaseResult changeCategouryCatgruop(GoodsCategory goodsCategory);
    /**
     * 改变分类的排序
     * @param goodsCategory
     * @return
     */
    BaseResult changeCategorySortOrder(GoodsCategory goodsCategory);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    GoodsCategory selectCategoryById(short id);

    /**
     * 更新分类信息
     * @param goodsCategory
     * @return
     */
    BaseResult updateForm(GoodsCategory goodsCategory);
}
