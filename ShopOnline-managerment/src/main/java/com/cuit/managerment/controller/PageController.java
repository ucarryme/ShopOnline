package com.cuit.managerment.controller;


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

        return "index";
    }

}
