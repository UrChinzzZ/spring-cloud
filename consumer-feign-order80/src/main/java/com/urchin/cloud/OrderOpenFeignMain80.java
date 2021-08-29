package com.urchin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-08-05 22:02
 */
@SpringBootApplication
//激活并开启openFeign
@EnableFeignClients
/*
启动 服务 降级 熔断 超时
 */
@EnableHystrix
public class OrderOpenFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignMain80.class,args);
    }
}