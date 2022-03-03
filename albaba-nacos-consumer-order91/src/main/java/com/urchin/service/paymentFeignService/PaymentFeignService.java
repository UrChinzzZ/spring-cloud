package com.urchin.service.paymentFeignService;

import com.urchin.service.paymentFallbackService.PaymentFallbackServiceImpl;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-08-05 22:06
 */
// 标注Spring管理的Bean，使用@Component注解在一个类上，表示将此类标记为Spring容器中的一个Bean
@Service
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackServiceImpl.class)
public interface PaymentFeignService {
    /**
     * fetch data by rule id
     * @Description:  *主键查询
     * @param id:  主键
     * @return: Payment 实体
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    @RequestMapping(value = "/paymentSQL/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id);


}