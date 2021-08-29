package com.urchin.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-17 21:41
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
//    //使用LoadBalanced注解 赋予 RestTemplate 负载均衡的能力
//    @LoadBalanced

    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}