package com.cuit.portal.controller;

import com.cuit.common.result.BaseResult;
import com.cuit.portal.entity.Users;
import com.cuit.portal.service.CartService;
import com.cuit.portal.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @RequestMapping("showCart")
    public String showCart(){
        return "test";
    }

    /**
     * 添加到购物车
     * @return
     */
    @ResponseBody
    @RequestMapping("addCart")
    public BaseResult addCart(CartVo cartVo, HttpServletRequest request){
        System.out.println("Cart:addCart添加的商品名为"+cartVo.getGoodsName());
        cartVo.setAddTime(new Date());
        Users users = (Users)request.getSession().getAttribute("users");
        System.out.println("CartController:addCar:users:"+users.getEmail());
        return cartService.addCart(cartVo,users);
    }

    /**
     *到底购物车商品数量
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("getCartNum")
    public Integer getCartNum(HttpServletRequest request){
        Users users = (Users)request.getSession().getAttribute("users");
        Integer cartNum = cartService.getCartNum(users);
        return cartNum;
    }
}
