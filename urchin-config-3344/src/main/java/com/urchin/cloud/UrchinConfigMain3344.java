package com.urchin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-08-29 21:01
 */
@SpringBootApplication
@EnableConfigServer
public class UrchinConfigMain3344 {
    public static void main(String[] args)
    {
        SpringApplication.run(UrchinConfigMain3344.class,args);
    }
}