package com.cuit.portal.service.impl;

import com.cuit.common.result.BaseResult;
import com.cuit.common.result.ShopPageInfo;
import com.cuit.portal.entity.Goods;
import com.cuit.portal.entity.GoodsExample;
import com.cuit.portal.mapper.GoodsMapper;
import com.cuit.portal.service.SearchService;
import com.cuit.portal.vo.GoodsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> doSearch(String searchStr, Integer pageNum, Integer pageSize) {
        ShopPageInfo<GoodsVo> pageInfo;
        //构建分类对象
        PageHelper.startPage(pageNum,pageSize);
        //构建查询对象
        GoodsExample goodsExample = new GoodsExample();

        //再同时查询选择的关键字
        if(!StringUtil.isEmpty(searchStr)) {
            goodsExample.createCriteria().andGoodsNameLike("%" + searchStr + "%");

        }
        List<Goods> list = goodsMapper.selectByExample(goodsExample);
        for (Goods goods : list) {
            System.out.println(goods.getMarketPrice());
        }
        if(!CollectionUtils.isEmpty(list)) {
            System.out.println("SearchService：查询到商品");
            return list;
        }else {
            System.out.println("查询无信息");
            return list;
        }
    }
}
