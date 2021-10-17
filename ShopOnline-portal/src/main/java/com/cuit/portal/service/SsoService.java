package com.cuit.portal.service;


import com.cuit.portal.entity.Users;

/**
 * 单点登录服务
 */
public interface SsoService {
    /**
     * 登录认证返回票据
     * @param users
     * @return
     */

    String login(Users users);

    /**
     * 验证票据
     * @param ticket
     * @return
     */
    Users validate(String ticket);

    /**
     * 用户退出
     * @param ticket
     */
    void logout(String ticket);
}
