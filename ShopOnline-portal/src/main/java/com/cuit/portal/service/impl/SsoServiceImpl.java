package com.cuit.portal.service.impl;


import com.cuit.common.utils.JsonUtil;
import com.cuit.common.utils.Md5Util;
import com.cuit.common.utils.UUIDUtil;
import com.cuit.portal.entity.Users;
import com.cuit.portal.entity.UsersExample;
import com.cuit.portal.mapper.AdminMapper;
import com.cuit.portal.mapper.UsersMapper;
import com.cuit.portal.service.SsoService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SsoServiceImpl implements SsoService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${my.ticket}")
    private String myTicket;
    /**
     * 登录认证返回票据
     * @param users
     * @return
     */
    @Override
    public String login(Users users) {
        System.out.println("进入身份验证");
        System.out.println("username:"+users.getEmail());
        System.out.println("password:"+users.getPassword());
        if(StringUtil.isEmpty(users.getEmail().trim())) {
            System.out.println("用户名为空");
            return null;
        }
        if(StringUtil.isEmpty(users.getPassword().trim())) {
            System.out.println("密码为空");
            return null;
        }
        UsersExample example = new UsersExample();
        example.createCriteria().andEmailEqualTo(users.getEmail());
        List<Users> userList = usersMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList) || userList.size() != 1){
            System.out.println("错误");
            return null;
        }
        Users users1 = userList.get(0);
        //判断密码
        if(!users1.getPassword().equals(Md5Util.getMd5WithSalt(users.getPassword(),"a2ck"))) {
            System.out.println("密码不匹配");
            return null;
        }
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String ticket = UUIDUtil.getUUID();
        System.out.println("ticket:"+ticket);
        valueOperations.set(myTicket+":"+ticket, JsonUtil.object2JsonStr(users1),30, TimeUnit.MINUTES);
        return ticket;
    }

    /**
     * 验证票据
     * @param ticket
     * @return
     */
    @Override
    public Users validate(String ticket) {
        if(StringUtil.isEmpty(ticket)){
            System.out.println("无票据信息");
            return  null;
        }
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String usersJson = valueOperations.get(myTicket + ":" + ticket);
        if(StringUtil.isEmpty(usersJson)){
            System.out.println("票据信息不匹配");
            return  null;
        }
        return JsonUtil.jsonStr2Object(usersJson,Users.class);
    }
    /**
     * 用户退出
     * @param ticket
     */
    @Override
    public void logout(String ticket) {
        redisTemplate.delete(myTicket+":"+ticket);

    }
}
