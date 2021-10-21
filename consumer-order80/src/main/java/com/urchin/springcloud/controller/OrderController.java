package com.urchin.springcloud.controller;

import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import com.urchin.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-17 21:38
 */
@RestController
@Slf4j
public class OrderController {
    public  static final  String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private  DiscoveryClient discoveryClient;
    @Autowired
    private  LoadBalancer loadBalancer;
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
            return  restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    /**
     * fetch data by rule id
    * @Description:  返回对象为响应体中的数据转换对象  基本上可以理解为json
    * @Param:
    * @return:
    * @author Mr.urchin
    * @Date: 2021/5/25
    */
    @GetMapping("/consumer/payment/get/{id}")
    public  CommonResult<Payment> getPayment(@PathVariable("id") long id){
            return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    /**
         * fetch data by rule id
        * @Description: 返回一个RsponseEntity的对象 包含相应的中的一些信息  比如响应头 相应状态码 响应体 等等
        * @Param:
        * @return:
        * @author Mr.urchin
        * @Date: 2021/5/25
    */
    @GetMapping("/consumer/payment/getEntity/{id}")
    public  CommonResult<Payment> getPayment2(@PathVariable("id") long id){
        ResponseEntity<CommonResult> entity=restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
                return  entity.getBody();
        }else {
         return    new CommonResult<>(4444,"查询失败");
        }
    }
    @GetMapping(value = "/consumer/payment/lb")
    public  String getPaymentLB(){
        List<ServiceInstance> serviceInstances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(serviceInstances==null||serviceInstances.size()<0){
            return null;
        }else {
            ServiceInstance serviceInstance= loadBalancer.instance(serviceInstances);
            URI uri=serviceInstance.getUri();
            return  restTemplate.getForObject(uri+"/payment/lb",String.class);
        }
    }
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin",String.class);
    }
}