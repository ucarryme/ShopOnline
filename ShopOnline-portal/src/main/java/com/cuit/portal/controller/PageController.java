package com.cuit.portal.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        System.out.println(page);
        return page;
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        System.out.println("空地址进入默认地址");
        return "index";
    }

}
