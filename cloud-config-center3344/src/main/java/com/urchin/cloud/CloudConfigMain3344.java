package com.urchin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-08-29 20:14
 */
@SpringBootApplication
@EnableConfigServer
public class CloudConfigMain3344 {
    public  static  void main(String[] args)
    {
        SpringApplication.run(CloudConfigMain3344.class,args);
    }
}