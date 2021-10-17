package com.cuit.managerment.service.implet;

import com.cuit.common.entity.Admin;
import com.cuit.common.entity.AdminExample;
import com.cuit.common.utils.JsonUtil;
import com.cuit.common.utils.Md5Util;
import com.cuit.common.utils.UUIDUtil;
import com.cuit.managerment.service.SsoService;
import com.cuit.managerment.mapper.AdminMapper;
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
    private AdminMapper adminMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${user.ticket}")
    private String userTicket;
    /**
     * 登录认证返回票据
     * @param admin
     * @return
     */

    @Override
    public String login(Admin admin) {
        System.out.println("进入身份验证");
        System.out.println("username:"+admin.getUserName());
        System.out.println("password:"+admin.getPassword());
        if(StringUtil.isEmpty(admin.getUserName().trim())) {
            System.out.println("用户名为空");
            return null;
        }
        if(StringUtil.isEmpty(admin.getPassword().trim())) {
            System.out.println("密码为空");
            return null;
        }
        AdminExample example = new AdminExample();
        example.createCriteria().andUserNameEqualTo(admin.getUserName());
        List<Admin> adminList = adminMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(adminList) || adminList.size() != 1){
            System.out.println("错误");
            return null;
        }
        Admin admin1 = adminList.get(0);
        //判断密码
        if(!admin1.getPassword().equals(Md5Util.getMd5WithSalt(admin.getPassword(),admin1.getEcSalt()))) {
            System.out.println("密码不匹配");
            return null;
        }
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String ticket = UUIDUtil.getUUID();
        System.out.println("ticket:"+ticket);
        valueOperations.set(userTicket+":"+ticket, JsonUtil.object2JsonStr(admin1),30, TimeUnit.MINUTES);
        return ticket;
    }

    /**
     * 验证票据
     * @param ticket
     * @return
     */
    @Override
    public Admin validate(String ticket) {
        if(StringUtil.isEmpty(ticket)){
            System.out.println("无票据信息");
            return  null;
        }
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String adminJson = valueOperations.get(userTicket + ":" + ticket);
        if(StringUtil.isEmpty(adminJson)){
            System.out.println("票据信息不匹配");
            return  null;
        }
        return JsonUtil.jsonStr2Object(adminJson,Admin.class);
    }
    /**
     * 用户退出
     * @param ticket
     */
    @Override
    public void logout(String ticket) {
        redisTemplate.delete(userTicket+":"+ticket);

    }
}
