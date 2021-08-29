package com.urchin.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.urchin.springcloud.dao.PaymentDao;
import com.urchin.springcloud.entities.Payment;
import com.urchin.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-16 22:44
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    final PaymentDao paymentDao;
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
    @Override
    public int save( Payment payment) {
        return paymentDao.save(payment);
    }
    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
    @Override
    public String paymentInfoOk(Integer id) {
        int age=10/0;
        return "线程池"+ Thread.currentThread().getName()+"paymentInfoOk,Id"+id+"\t"+"O(∩_∩)O";
    }
    @Override
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "10000")
//    })
    public String paymentInfoTimeOut(Integer id) {
        // 模拟超时
//        int sleepTime=5;
//        try {
//            TimeUnit.SECONDS.sleep(sleepTime);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        // 模拟服务异常
//        int age=10/0;
        return "线程池"+ Thread.currentThread().getName()+"paymentInfoTimeOut,Id"+id+"\t"+"O(∩_∩)O呜呜"+"耗时(秒)";
    }

    public String paymentInfoTimeOutHandler(Integer id){

        return "线程池"+ Thread.currentThread().getName()+"系统正忙,请稍后,Id:"+id+"\t"+"┭┮﹏┭┮呜呜"+"耗时(秒)";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED,value = "true"),//是否开启断路器
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "10"),//单位时间内（默认10s内），请求超时数超出则触发熔断策略。默认值为20次请求数。
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value = "10000"),//当熔断策略开启后，延迟多久尝试再次请求远程服务。默认为5秒。单位毫秒。这5秒直接执行fallback方法，不在请求远程application service。
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "50")//单位时间内，出现错误的请求百分比达到限制，则触发熔断策略。默认为50%。
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        if(id<0){
                 throw  new RuntimeException("***ID不可为负数***");
        }
        String serialNumber= IdUtil.simpleUUID();
        return  Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id){
        return  "***ID不可为负数***┭┮﹏┭┮"+id;
    }

}