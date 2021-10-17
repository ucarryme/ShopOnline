package com.cuit.sso;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.cuit.sso.mapper")
public class SsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class,args);
    }
}
