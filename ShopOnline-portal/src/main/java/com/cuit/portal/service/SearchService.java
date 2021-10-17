package com.cuit.portal.service;

import com.cuit.common.result.BaseResult;
import com.cuit.common.result.ShopPageInfo;
import com.cuit.portal.entity.Goods;
import com.cuit.portal.vo.GoodsVo;

import java.util.List;

public interface SearchService {
    /**
     * 搜索
     * @param searchStr
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Goods> doSearch(String searchStr, Integer pageNum, Integer pageSize);
}
