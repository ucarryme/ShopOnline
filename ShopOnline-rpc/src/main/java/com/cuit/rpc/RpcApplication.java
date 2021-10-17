package com.cuit.rpc;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//开启dubbo
@EnableDubboConfiguration
@EnableDubbo
@MapperScan("com.cuit.rpc.mapper")
public class RpcApplication {
    public static void main(String[] args) {
        SpringApplication.run(RpcApplication.class,args);
    }
}
