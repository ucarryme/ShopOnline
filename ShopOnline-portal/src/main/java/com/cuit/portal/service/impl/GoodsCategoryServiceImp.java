package com.cuit.portal.service.impl;

import com.cuit.common.utils.JsonUtil;

import com.cuit.portal.entity.GoodsCategory;
import com.cuit.portal.entity.GoodsCategoryExample;
import com.cuit.portal.mapper.GoodsCategoryMapper;
import com.cuit.portal.service.GoodsCategoryService;
import com.cuit.portal.vo.GoodsCategoryVo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsCategoryServiceImp implements GoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${goods.category.list.key}")
    private String goodsListKey;
    /**
     * 商品分类-列表
     * @return
     */
    @Override
    public List<GoodsCategoryVo> selectCategoryListForView() {


        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        //查询Redis是否缓存分类数据,若有则直接返回，若无再去查询
        String gcvListJson = valueOperations.get(goodsListKey);
        if(!StringUtil.isEmpty(gcvListJson)){
            return JsonUtil.jsonToList(gcvListJson,GoodsCategoryVo.class);
        }

        GoodsCategoryExample example = new GoodsCategoryExample();
        //查询所以顶级分类
        example.createCriteria().andParentIdEqualTo((short) 0).andLevelEqualTo((byte) 1);
        List<GoodsCategory> gcList = goodsCategoryMapper.selectByExample(example);
        List<GoodsCategoryVo> gcvList = new ArrayList<>();
        for (GoodsCategory gc1 : gcList) {
            GoodsCategoryVo gcv1 = new GoodsCategoryVo();
            //把GoodsCategory对象转成GoodsCategoryVo对象
            BeanUtils.copyProperties(gc1, gcv1);
            //清空example
            example.clear();
            //查询二级分类
            example.createCriteria().andParentIdEqualTo(gc1.getId()).andLevelEqualTo((byte) 2);
            List<GoodsCategory> gcList2 = goodsCategoryMapper.selectByExample(example);
            List<GoodsCategoryVo> gcvList2 = new ArrayList<>();
            for (GoodsCategory gc2 : gcList2) {
                GoodsCategoryVo gcv2 = new GoodsCategoryVo();
                BeanUtils.copyProperties(gc2, gcv2);
                //清空example
                example.clear();
                //查询三级级分类
                example.createCriteria().andParentIdEqualTo(gc2.getId()).andLevelEqualTo((byte) 3);
                List<GoodsCategory> gcList3 = goodsCategoryMapper.selectByExample(example);
                List<GoodsCategoryVo> gcvList3 = new ArrayList<>();
                for (GoodsCategory gc3 : gcList3) {
                    GoodsCategoryVo gcv3 = new GoodsCategoryVo();
                    BeanUtils.copyProperties(gc3, gcv3);
                    gcvList3.add(gcv3);
                }
                //把三级分类放入二级分类对象中
                gcv2.setChildren(gcvList3);
                gcvList2.add(gcv2);
            }
            //把二级分类放入一级分类对象中
            gcv1.setChildren(gcvList2);
            gcvList.add(gcv1);
        }

        //放入Redis缓存
        valueOperations.set(goodsListKey,JsonUtil.object2JsonStr(gcvList));

        return  gcvList;
    }
}
