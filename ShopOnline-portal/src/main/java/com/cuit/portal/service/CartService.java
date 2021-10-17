package com.cuit.portal.service;

import com.cuit.common.result.BaseResult;
import com.cuit.portal.entity.Users;
import com.cuit.portal.vo.CartVo;

/**
 * 购物车
 */
public interface CartService {
    /**
     * 加入购物车
     * @param cartVo
     * @param users
     * @return
     */
    BaseResult addCart(CartVo cartVo, Users users);

    /**
     * 查询购物车商品数量
     * @param users
     * @return
     */
    Integer getCartNum(Users users);
}
