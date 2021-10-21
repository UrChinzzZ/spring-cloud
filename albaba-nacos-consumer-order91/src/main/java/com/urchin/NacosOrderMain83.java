package com.urchin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author urchin
 * @Description 程序主入口
 * @PROJECT_NAME evolution
 * @create 2021-10-20 21:18
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosOrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain83.class,args);
    }
}