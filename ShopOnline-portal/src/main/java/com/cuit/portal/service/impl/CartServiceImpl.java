package com.cuit.portal.service.impl;

import com.cuit.common.result.BaseResult;
import com.cuit.common.utils.JsonUtil;
import com.cuit.portal.entity.Users;
import com.cuit.portal.service.CartService;
import com.cuit.portal.vo.CartVo;
import com.github.pagehelper.util.StringUtil;
import javafx.geometry.VPos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${user.cart}")
    private String userCart;
    private HashOperations<String, String, String> hashOperations = null;

    /**
     * 加入购物车
     * @param cartVo
     * @param users
     * @return
     */
    @Override
    public BaseResult addCart(CartVo cartVo, Users users) {
        if(null ==users || null == users.getUserId()) {
            System.out.println("CartService：用户不存在");
            return BaseResult.error();
        }
        //查询当前用户的购物车信息
        Integer userId = users.getUserId();
        hashOperations = redisTemplate.opsForHash();
        System.out.println(userCart + ":" + userId);
        Map<String, String> cartMap = hashOperations.entries(userCart + ":" + userId);
        if(!CollectionUtils.isEmpty(cartMap)){
            //购物车不为空
            System.out.println("CartService：购物车信息存在");
            String cartStr = cartMap.get(String.valueOf(cartVo.getGoodsId()));
            //购物车内商品存在，添加重复商品，需要修改商品数量和价格
            if(!StringUtil.isEmpty(cartStr)){
                //查询到商品信息
                CartVo vo = JsonUtil.jsonStr2Object(cartStr, CartVo.class);
                if(vo != null) {
                    //修改商品数量
                    vo.setGoodsNum(vo.getGoodsNum() + cartVo.getGoodsNum());
                    System.out.println("CartService：商品数量"+vo.getGoodsNum());
                    //修改商品最新的价格
                    vo.setMarketPrice(cartVo.getMarketPrice());
                    //覆盖以前的商品对象
                    cartMap.put(String.valueOf(vo.getGoodsId()), JsonUtil.object2JsonStr(vo));
                    System.out.println("CartService：商品存在");
                }else
                    System.out.println("CartService：商品不存在");
            }else {
                System.out.println("CartService：现购物车无商品,新增商品信息到购物车");
                cartMap.put(String.valueOf(cartVo.getGoodsId()),JsonUtil.object2JsonStr(cartVo));
            }

        }else{
            //购物车为空
            cartMap = new HashMap<>();
            System.out.println("CartService：新增购物车信息");
            cartMap.put(String.valueOf(cartVo.getGoodsId()),JsonUtil.object2JsonStr(cartVo));
        }
        hashOperations.putAll(userCart+":"+userId,cartMap);
        return BaseResult.success();
    }
    /**
     * 查询购物车商品数量
     * @param users
     * @return
     */
    @Override
    public Integer getCartNum(Users users) {
        System.out.println("CartService：getCartNum");
        if(null ==users || null == users.getUserId() ) {
            System.out.println("CartService：用户不存在");
            return 0;
        }
        Integer result = 0;
        hashOperations = redisTemplate.opsForHash();
        Map<String, String> cartMap = hashOperations.entries(userCart + ":" + users.getUserId());
        if(!CollectionUtils.isEmpty(cartMap)){
            for (Map.Entry<String, String> entry : cartMap.entrySet()) {
                CartVo cartVo = JsonUtil.jsonStr2Object(entry.getValue(), CartVo.class);
                result += cartVo.getGoodsNum();
            }
        }

        return result;
    }
}
