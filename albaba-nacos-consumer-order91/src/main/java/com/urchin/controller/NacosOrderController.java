package com.urchin.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.urchin.myHandler.CustomerBlockHandler;
import com.urchin.service.paymentFeignService.PaymentFeignService;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
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
    @Resource
    private RestTemplate restTemplate;
    //注册到微服务提供者名称中
    @Value("${service-url}")
    private  String serverURL;
    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id, String.class);
    }
//    @SentinelResource(value = "fallback",fallback = "handlerException")
    @GetMapping("/consumer/paymentSQL/{id}")
    @SentinelResource(value = "fallback",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id){
        return paymentFeignService.getPaymentById(id);
    }
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(444,"按自定义熔断方法启动=====》handlerException");
    }
}