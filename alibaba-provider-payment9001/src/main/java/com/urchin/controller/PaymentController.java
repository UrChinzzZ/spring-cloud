package com.urchin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-10-20 22:08
 */
@RestController

public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id)
    {
        return "nacos registry,serverPort:"+serverPort+"\t id"+id;
    }
}