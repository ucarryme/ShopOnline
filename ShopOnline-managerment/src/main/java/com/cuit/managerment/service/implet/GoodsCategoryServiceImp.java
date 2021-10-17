package com.cuit.managerment.service.implet;

import com.cuit.common.result.BaseResult;
import com.cuit.common.utils.JsonUtil;
import com.cuit.managerment.entity.Goods;
import com.cuit.managerment.entity.GoodsCategory;
import com.cuit.managerment.entity.GoodsCategoryExample;
import com.cuit.managerment.mapper.GoodsCategoryMapper;
import com.cuit.managerment.service.GoodsCategoryService;
import com.cuit.managerment.vo.GoodsCategoryVo;
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
     * 商品分类-新增商品-查询顶级分类
     *
     * @return
     */
    @Override
    public List<GoodsCategory> selectCategoryTopList() {
        //创建查询对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        //设置查询条件，parent_id == 0
        example.createCriteria().andParentIdEqualTo((short)0);

        return goodsCategoryMapper.selectByExample(example);
    }
    /**
     * 商品分类-新增商品-级联查询
     * @param parentId
     * @return
     */
    @Override
    public List<GoodsCategory> selectCategoryByParentId(Short parentId) {
        //创建查询对象
        GoodsCategoryExample example = new GoodsCategoryExample();
        //设置查询条件，parent_id == parentId
        example.createCriteria().andParentIdEqualTo(parentId);
        return goodsCategoryMapper.selectByExample(example);
    }

    /**
     * 新增分类
     * @param goodsCategory
     * @return
     */
    @Override
    public int categorySave(GoodsCategory goodsCategory) {
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));
        return goodsCategoryMapper.insertSelective(goodsCategory);
    }
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

//        //======================jdk8新特性=============================
//        GoodsCategoryExample example = new GoodsCategoryExample();
//        //查询所有分类
//        List<GoodsCategory> list = goodsCategoryMapper.selectByExample(example);
//        //把GoodsCategory对象转成GoodsCategoryVo对象
//        List<GoodsCategoryVo> gcvList = list.stream().map(e->{
//            GoodsCategoryVo gcv = new GoodsCategoryVo();
//            BeanUtils.copyProperties(e,gcv);
//            return gcv;
//        }).collect(Collectors.toList());
//        //将List<GoodsCategoryVo>转成Map<parentId,List<GoodsCategoryVo>>
//        //按parentId分组，key就是parentId，值就是parentId对应的list<GoodsCategoryVo>
//        System.out.println(gcvList.get(844).getMobileName());
//        Map<Short,List<GoodsCategoryVo>> map = gcvList.stream().collect(Collectors.groupingBy(GoodsCategoryVo::getParentId));
//
//        //循环给children赋值
//        gcvList.forEach(e->e.setChildren(map.get(e.getId())));
//        //拦截器，返回顶级分类(level为1)的list
//        List<GoodsCategoryVo>gcvList1 = gcvList.stream().filter(e->1 == e.getLevel()).collect(Collectors.toList());
//        //======================jdk8新特性=============================
//        return gcvList1;
//    }

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Override
    public GoodsCategory categorySelectOne(Short id) {
        GoodsCategoryExample example = new GoodsCategoryExample();
         return goodsCategoryMapper.selectByPrimaryKey(id);
}

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @Override
    public int categoryDelete(Short id) {
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));
        return goodsCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<GoodsCategory> selectCategoryList() {
        return goodsCategoryMapper.selectByExample(new GoodsCategoryExample());
    }
    /**
     * 改变分类的推荐
     * @param goodsCategory
     * @return
     */
    @Override
    public BaseResult changeCategoryIshot(GoodsCategory goodsCategory) {
        if(null == goodsCategory.getIsHot())
            return BaseResult.error();
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory)>0?BaseResult.success():BaseResult.error();
    }
    /**
     * 改变分类的展示
     * @param goodsCategory
     * @return
     */
    @Override
    public BaseResult changeCategoryIsshow(GoodsCategory goodsCategory) {
        if(null == goodsCategory.getIsShow())
            return BaseResult.error();
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory)>0?BaseResult.success():BaseResult.error();
    }
    /**
     * 改变分类的分组
     * @param goodsCategory
     * @return
     */
    @Override
    public BaseResult changeCategouryCatgruop(GoodsCategory goodsCategory) {
        if(null == goodsCategory.getCatGroup())
            return BaseResult.error();
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory)>0?BaseResult.success():BaseResult.error();
    }
    /**
     * 改变分类的排序
     * @param goodsCategory
     * @return
     */
    @Override
    public BaseResult changeCategorySortOrder(GoodsCategory goodsCategory) {
        if(null == goodsCategory.getSortOrder())
            return  BaseResult.error();
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory)>0?BaseResult.success():BaseResult.error();
    }
    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Override
    public GoodsCategory selectCategoryById(short id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }
    /**
     * 更新分类信息
     * @param goodsCategory
     * @return
     */
    @Override
    public BaseResult updateForm(GoodsCategory goodsCategory) {
        //删除Redis缓存
        redisTemplate.delete(redisTemplate.keys("goods*"));
        return  goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory)>0?BaseResult.success():BaseResult.error();
    }
}
