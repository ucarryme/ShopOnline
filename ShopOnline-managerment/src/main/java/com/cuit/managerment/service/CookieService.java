package com.cuit.managerment.service;

import com.github.pagehelper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie
 */
public interface CookieService {
    /**
     *
     * @param request
     * @param response
     * @param ticket
     * @return
     */
    boolean setCookie(HttpServletRequest request, HttpServletResponse response, String ticket);

    /**
     * 获取cookie
     * @param request
     * @return
     */
    String getCookie(HttpServletRequest request);

    /**
     * 删除cookie
     * @param request
     * @return
     */
    boolean deleteCookie(HttpServletRequest request,HttpServletResponse response);
}
