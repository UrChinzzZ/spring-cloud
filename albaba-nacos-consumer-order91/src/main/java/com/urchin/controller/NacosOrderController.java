package com.urchin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-10-20 23:25
 */
@RestController
public class NacosOrderController {
    //注册到微服务提供者名称中
    public  static final  String PAYMENT_URL="http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;
    @Value("${service-url}")
    private  String serverURL;
    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id, String.class);
    }
}