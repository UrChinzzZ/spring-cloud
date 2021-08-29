package com.urchin.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author urchin
 * @Description 程序主入口
 * @PROJECT_NAME evolution
 * @create 2021-05-16 21:18
 */
@SpringBootApplication
/*
 * 注册 Eureka注册中心
 *
 */
@EnableEurekaClient
/*
 * 注册 其他的注册中心 更加推荐
 * @EnableDiscoveryClient
 */
/*
 * 启动 降级 限流 熔断 服务
 *
 */
@EnableHystrix
@EnableCircuitBreaker
public class PaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}