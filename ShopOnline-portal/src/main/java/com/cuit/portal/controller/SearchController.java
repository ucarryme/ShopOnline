package com.cuit.portal.controller;

import com.cuit.portal.entity.Goods;
import com.cuit.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 进入搜索页面
     * @param searchStr
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(String searchStr, Model model){
        model.addAttribute("searchStr",searchStr);
        return "search/doSearch";

    }

    /**
     * 渲染搜索页面
     * @param searchStr
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("searchGoods")
    public List<Goods> searchGoodsByName(String searchStr, Integer pageNum, Integer pageSize){
        System.out.println("搜索的商品名："+searchStr);
        System.out.println("分页大小："+pageSize);


        return searchService.doSearch(searchStr,pageNum,pageSize);
    }
}
