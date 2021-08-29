package com.urchin.cloud.service.paymentFeignService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.urchin.cloud.service.paymentFallbackService.PaymentFallbackServiceImpl;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-08-05 22:06
 */
// 标注Spring管理的Bean，使用@Component注解在一个类上，表示将此类标记为Spring容器中的一个Bean
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = PaymentFallbackServiceImpl.class)
public interface PaymentFeignService {
    /**
     * fetch data by rule id
     * @Description:  *主键查询
     * @param id:  主键
     * @return: Payment 实体
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    @RequestMapping(value = "payment/get/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id);
    /**
     * fetch data by rule id
     * @Description: 新增
     * @param  payment:实体
     * @return  int 查询
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    @RequestMapping(value = "payment/create", method = RequestMethod.POST)
    public  CommonResult save(@RequestBody Payment payment);
    /**
     * fetch data by rule id
    * @Description:  超时控制
    * @Param:
    * @return:
    * @author Mr.urchin
    * @Date: 2021/8/17
    */
    @RequestMapping(value = "/payment/feign/timeout",method = RequestMethod.GET)
    public String paymentFeignTimeout();
    /**
     * fetch data by rule id
     * @Description: 正常访问
     * @param id:  参数
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/25
     */
    @GetMapping(value = "/payment/Hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id);
    /**
     * fetch data by rule id
     * @Description:  超时访问  模拟出错
     * @param id:  参数
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/25
     */

    @GetMapping(value = "/payment/Hystrix/timeOut/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Integer id);
    /**
     * fetch data by rule id
     * @Description: 服务熔断
     * @Param:
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/26
     */
    @RequestMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);

}