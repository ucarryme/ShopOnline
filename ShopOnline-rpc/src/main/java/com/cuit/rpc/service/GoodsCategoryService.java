package com.cuit.rpc.service;


import com.cuit.rpc.vo.GoodsCategoryVo;


import java.util.List;
public interface GoodsCategoryService {

    /**
     * 商品分类-列表
     * @return
     */
    List<GoodsCategoryVo> selectCategoryListForView();

}
