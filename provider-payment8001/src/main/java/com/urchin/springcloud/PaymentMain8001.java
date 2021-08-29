package com.urchin.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

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
@MapperScan("com.urchin.springcloud.dao")
@ComponentScan(basePackages = {"cn.urchin"})
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}