package com.cuit.portal;

import com.cuit.portal.entity.Users;
import com.cuit.portal.service.CartService;
import com.cuit.portal.vo.CartVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartService cartService;
    @Test
    public void testAddCart(){
        Users users = new Users();
        users.setUserId(1);
        CartVo cartVo = new CartVo();
        cartVo.setGoodsId(2356);
        cartVo.setGoodsName("java");
        cartVo.setGoodsNum(10);
        cartVo.setMarketPrice(new BigDecimal("100"));
        cartVo.setAddTime(new Date());
        cartService.addCart(cartVo,users);

    }
    @Test
    public void testGetCartNum(){
        Users users = new Users();
        users.setUserId(1);
        Integer cartNum = cartService.getCartNum(users);
        System.out.println(cartNum);


    }
}
