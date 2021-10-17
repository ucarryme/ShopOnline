package com.cuit.managerment.service.implet;

import com.cuit.common.result.BaseResult;
import com.cuit.common.utils.JsonUtil;
import com.cuit.managerment.entity.Goods;
import com.cuit.managerment.entity.GoodsExample;
import com.cuit.managerment.mapper.GoodsMapper;
import com.cuit.managerment.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import javax.rmi.CORBA.Util;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImple implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    String goodsListKey;
    /**
     * 保存商品
     * @param goods
     * @return
     */
    @Override
    public BaseResult saveGoods(Goods goods) {
        //判断是否连续添加了两次相同信息的商品
        if(null != goods.getGoodsId()){
            return BaseResult.error();
        }
        //html文本转义
        if(!StringUtil.isEmpty(goods.getGoodsContent())){
            goods.setGoodsContent(HtmlUtils.htmlEscape(goods.getGoodsContent(),"UTF-8"));
        }
        int result = goodsMapper.insertSelective(goods);
        BaseResult baseResult = BaseResult.error();
        if(0<result){
            baseResult = BaseResult.success();
            baseResult.setMessage(String.valueOf(goods.getGoodsId()));
        }else
            baseResult = BaseResult.error();
        return baseResult;
    }
    /**
     * 商品列表-分页显示
     * @param goods
     * @param page
     * @param size
     * @return
     */
    @Override
    public BaseResult selectGoodsByPage(Goods goods, Integer page, Integer size) {
        String[] goodsKeyArr = new String[]{
                "goods:pageNum_"+page+":pageSize_"+size+":",
                "catId_:",
                "brandId_:",
                "goodName_:"
        };

        //构建分类对象
        PageHelper.startPage(page,size);
        //构建查询对象
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        //查询选择的分类
        if(null !=goods.getCatId() && 0 !=goods.getCatId()){
            criteria.andCatIdEqualTo(goods.getCatId());
            goodsKeyArr[1] = "catId_"+goods.getCatId()+":";
        }
        //同时查询选择的品牌
        if(null !=goods.getBrandId() && 0 !=goods.getBrandId()) {
            criteria.andBrandIdEqualTo(goods.getBrandId());
            goodsKeyArr[2] = "brandId_"+goods.getBrandId()+":";
        }
        //再同时查询选择的关键字
        if(!StringUtil.isEmpty(goods.getGoodsName())) {
            criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
            goodsKeyArr[3] = "goodName"+goods.getGoodsName()+":";
        }
        goodsListKey = Arrays.stream(goodsKeyArr).collect(Collectors.joining());
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String pageInfoGoodsJson = valueOperations.get(goodsListKey);
        if(!StringUtils.isEmpty(pageInfoGoodsJson)){
            return BaseResult.success(JsonUtil.jsonStr2Object(pageInfoGoodsJson,PageInfo.class));
        }
        List<Goods> list = goodsMapper.selectByExample(goodsExample);
        if(!CollectionUtils.isEmpty(list)) {
            PageInfo<Goods> pageInfo = new PageInfo<>(list);
            valueOperations.set(goodsListKey,JsonUtil.object2JsonStr(pageInfo));
            return BaseResult.success(pageInfo);
        }else {
             valueOperations.set(goodsListKey,JsonUtil.object2JsonStr(new PageInfo<>
                    (new ArrayList<Goods>())),60, TimeUnit.SECONDS);
        }
        return BaseResult.error();
    }
    /**
     * 商品列表-根据id删除商品
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteGoodsById(Integer id) {
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys(goodsListKey));
        return goodsMapper.deleteByPrimaryKey(id)>0?BaseResult.success():BaseResult.error();
    }


}
