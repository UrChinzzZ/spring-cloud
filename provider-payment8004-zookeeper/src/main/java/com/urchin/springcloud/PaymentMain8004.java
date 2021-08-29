package com.urchin.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author urchin
 * @Description 程序主入口
 * @PROJECT_NAME evolution
 * @create 2021-05-16 21:18
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.urchin.springcloud.dao")
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}