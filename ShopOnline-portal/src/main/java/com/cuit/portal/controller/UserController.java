package com.cuit.portal.controller;

import com.cuit.common.result.BaseResult;
import com.cuit.common.utils.CookieUtil;
import com.cuit.portal.entity.Users;
import com.cuit.portal.service.CookieService;
import com.cuit.portal.service.SsoService;
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
    private CookieService cookieService;
    @Autowired
    private SsoService ssoService;
    @ResponseBody
    @RequestMapping("login")
    public BaseResult login(Users users, HttpServletRequest request, HttpServletResponse response){
        System.out.println("用户登录");
        System.out.println("email"+users.getPassword());
        //登录成功生成票据
        String ticket = ssoService.login(users);
        //有票据写入cookie
        if(!StringUtil.isEmpty(ticket)){
            System.out.println("email:"+users.getEmail());
            //将用户信息传入session中
            request.getSession().setAttribute("users",users);
            return cookieService.setCookie(request,response,ticket)?BaseResult.success():BaseResult.error();
        }
        System.out.println("users为空");
        return  BaseResult.error();
    }
    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        String ticket = cookieService.getCookie(request);
        if(StringUtil.isEmpty(ticket))
            return "error";
        ssoService.logout(ticket);
        request.getSession().removeAttribute("users");
        cookieService.deleteCookie(request,response);
        return "login";
    }
}
