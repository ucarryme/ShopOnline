package com.cuit.managerment;

import com.cuit.managerment.service.GoodsCategoryService;
import com.cuit.managerment.vo.GoodsCategoryVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.HtmlUtils;

import javax.swing.text.html.HTMLEditorKit;
import java.util.List;

@SpringBootTest
public class GoodsCategoryServiceTest {
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Test
    public void testSelectCategoryListForView(){
        List<GoodsCategoryVo> gcvList = goodsCategoryService.selectCategoryListForView();
        System.out.println(gcvList);

    }
    @Test
    public  void  testHtlm(){
        String html = HtmlUtils.htmlEscape("<p>测试html文明转义</p>","UTF-8");
        System.out.println(html);
        System.out.println(HtmlUtils.htmlUnescape(html));
    }


}
