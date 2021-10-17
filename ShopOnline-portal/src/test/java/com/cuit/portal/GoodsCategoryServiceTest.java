package com.cuit.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cuit.rpc.service.GoodsCategoryService;
import com.cuit.rpc.vo.GoodsCategoryVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GoodsCategoryServiceTest {
    @Reference(interfaceClass = GoodsCategoryService.class)
    private GoodsCategoryService goodsCategoryService;
    @Test
    public void testCategoryListForView(){
        List<GoodsCategoryVo> list = goodsCategoryService.selectCategoryListForView();
        System.out.println(list);


    }
}
