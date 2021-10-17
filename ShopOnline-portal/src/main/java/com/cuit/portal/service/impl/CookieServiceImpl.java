package com.cuit.portal.service.impl;

import com.cuit.common.utils.CookieUtil;
import com.cuit.portal.service.CookieService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieServiceImpl implements CookieService {
    /**
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     */
    @Override
    public boolean setCookie(HttpServletRequest request, HttpServletResponse response, String ticket) {
        try {
            CookieUtil.setCookie(request,response,"myTicket",ticket);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    /**
     * 获取cookie
     * @param request
     * @return
     */
    @Override
    public String getCookie(HttpServletRequest request) {
        return CookieUtil.getCookieValue(request,"myTicket");
    }
    /**
     * 删除cookie
     * @param request
     * @return
     */
    @Override
    public boolean deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.deleteCookie(request,response,"myTicket");
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
