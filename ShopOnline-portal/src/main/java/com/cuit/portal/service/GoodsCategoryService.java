package com.cuit.portal.service;


import com.cuit.portal.vo.GoodsCategoryVo;

import java.util.List;

public interface GoodsCategoryService {

    /**
     * 商品分类-列表
     * @return
     */
    List<GoodsCategoryVo> selectCategoryListForView();

}
