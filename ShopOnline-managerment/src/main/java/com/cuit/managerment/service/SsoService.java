package com.cuit.managerment.service;

import com.cuit.common.entity.Admin;

/**
 * 单点登录服务
 */
public interface SsoService {
    /**
     * 登录认证返回票据
     * @param admin
     * @return
     */

    String login(Admin admin);

    /**
     * 验证票据
     * @param ticket
     * @return
     */
    Admin validate(String ticket);

    /**
     * 用户退出
     * @param ticket
     */
    void logout(String ticket);
}
