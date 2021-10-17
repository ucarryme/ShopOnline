package com.cuit.managerment.controller;

import com.cuit.common.entity.Admin;
import com.cuit.common.result.BaseResult;
import com.cuit.managerment.service.CookieService;
import com.cuit.managerment.service.SsoService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private SsoService ssoService;
    @Autowired
    private CookieService cookieService;
    @RequestMapping("login")
    @ResponseBody
    public BaseResult login(Admin admin, HttpServletRequest request, HttpServletResponse response){
        //登录成功生成票据
        String ticket = ssoService.login(admin);
        //有票据写入cookie
        if(!StringUtil.isEmpty(ticket)){
            System.out.println("username:"+admin.getUserName());
            //将用户信息传入session中
            request.getSession().setAttribute("user",admin);
            return cookieService.setCookie(request,response,ticket)?BaseResult.success():BaseResult.error();
        }
        System.out.println("admin为空");
        return BaseResult.error();
    }
    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        String ticket = cookieService.getCookie(request);
        if(StringUtil.isEmpty(ticket))
            return "error";
        ssoService.logout(ticket);
        request.getSession().removeAttribute("user");
        cookieService.deleteCookie(request,response);
        return "login";
    }
}
