package com.urchin.springcloud;

import com.urchin.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * fetch data by rule id
* @Description:  程序主入口
* @author Mr.urchin
* @Date: 2021/5/17
*/
@SpringBootApplication()
@EnableEurekaClient
//自定义负载均衡规则
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)
public class ConsumerOrder80Application {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80Application.class, args);
    }

}
