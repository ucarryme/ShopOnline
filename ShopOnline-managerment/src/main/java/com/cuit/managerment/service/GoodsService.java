package com.cuit.managerment.service;

import com.cuit.common.result.BaseResult;
import com.cuit.managerment.entity.Goods;

public interface GoodsService {
    /**
     * 保存商品
     * @param goods
     * @return
     */
    BaseResult saveGoods(Goods goods);

    /**
     * 商品列表-分页显示
     * @param goods
     * @param page
     * @param size
     * @return
     */
    BaseResult selectGoodsByPage(Goods goods,Integer page,Integer size);

    /**
     * 商品列表-根据id删除商品
     * @param id
     * @return
     */
    BaseResult deleteGoodsById(Integer id);
}
