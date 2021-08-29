package com.urchin.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.urchin.cloud.service.paymentFeignService.PaymentFeignService;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-08-05 22:19
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback="paymentGlobalFallbackMethod")
public class OrderOpenFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;
    @RequestMapping(value = "consumer/payment/get/{id}", method = RequestMethod.GET)
    /**
     * fetch data by rule id
     * @Description:  *主键查询
     * @param id:  主键
     * @return: Payment 实体
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        return paymentFeignService.getPaymentById(id);
    }
    /**
     * fetch data by rule id
     * @Description: 新增
     * @param  payment:实体
     * @return  int 查询
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    @RequestMapping(value = "consumer/payment/create", method = RequestMethod.POST)
    public CommonResult save(@RequestBody Payment payment){
        return paymentFeignService.save(payment);
    }
    @RequestMapping(value = "consumer/payment/feign/timeout",method = RequestMethod.GET)
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
    /**
     * fetch data by rule id
     * @Description:  超时访问  模拟出错
     * @param id:  参数
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/25
     */
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @RequestMapping(value = "/consumer/payment/Hystrix/TimeOut/{id}",method = RequestMethod.GET)
    @HystrixCommand
    public String paymentInfoTimeOut(@PathVariable("id") Integer id)
    {
        int age=10/0;
        return paymentFeignService.paymentInfoTimeOut(id);
    }
    /**
     * fetch data by rule id
     * @Description: 正常访问
     * @param id:  参数
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/25
     */
    @RequestMapping(value = "/consumer/payment/Hystrix/ok/{id}",method = RequestMethod.GET)
    @HystrixCommand
    public String paymentInfoOk(@PathVariable("id") Integer id)
    {
        int age=10/0;
        return paymentFeignService.paymentInfoOk(id);
    }
    /**
     * fetch data by rule id
     * @Description:  服务降级方案
     * @param id:  参数
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/25
     */
    public String paymentInfoTimeOutHandler(Integer id){
        return "线程池"+ Thread.currentThread().getName()+"系统正忙,请稍后,Id:"+id+"\t"+"┭┮﹏┭┮呜呜"+"耗时(秒)";
    }
    /**
     * fetch data by rule id
     * @Description:  全局服务降级方法
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/25
     */
    public String paymentGlobalFallbackMethod(){
        return "全局异常处理:服务正忙或者出现错误。请稍后再试";
    }

    /**
     * fetch data by rule id
     * @Description: 服务熔断
     * @Param:
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/26
     */
    @RequestMapping("/consumer/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        int age=10/0;
        return paymentFeignService.paymentCircuitBreaker(id);
    }
}