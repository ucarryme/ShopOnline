package com.cuit.portal.interceptor;


import com.cuit.common.utils.CookieUtil;
import com.cuit.common.utils.JsonUtil;
import com.cuit.portal.entity.Users;
import com.cuit.portal.service.SsoService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 */
@Component
public class PortalLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SsoService ssoService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${my.ticket}")
    private String myTicket;
    /**
     * 处理请求方法之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtil.getCookieValue(request, "myTicket");
        System.out.println("拦截器ticket:"+ticket);
        if(!StringUtil.isEmpty(ticket)){
            Users users = ssoService.validate(ticket);
            request.getSession().setAttribute("users",users);
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(myTicket+":"+ticket, JsonUtil.object2JsonStr(users),30, TimeUnit.MINUTES);
            return true;
        }
        System.out.println("跳到登录界面");
        response.sendRedirect(request.getContextPath()+"/login");
        return false;
    }

    /**
     * 请求处理的方法之后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 处理后执行清理工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
