package com.urchin.controller;

import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
    public  static HashMap<Long, Payment> hashMap =new  HashMap<>();
    static {
        hashMap.put(1L,new  Payment(1L,"我是数据一"));
        hashMap.put(2L,new  Payment(2L,"我是数据二"));
        hashMap.put(3L,new  Payment(3L,"我是数据三"));
    }
    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id)
    {
        return "nacos registry,serverPort:"+serverPort+"\t id"+id;
    }
    @GetMapping(value ="/paymentSQL/{id}" )
    public CommonResult<Payment> paymentSQL(@PathVariable("id") long id)
    {
        Payment payment=hashMap.get(id);
        CommonResult<Payment> result=new CommonResult<Payment>(200,"from SQL payment server:"+serverPort,payment);
        return result;
    }
}