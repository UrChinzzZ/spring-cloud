package com.urchin.cloud.service.paymentFallbackService;

import com.urchin.cloud.service.paymentFeignService.PaymentFeignService;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author urchin
 * @Description  openFeign接口 服务 降级熔断超时 处理类
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-08-25 23:32
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentFeignService {
    @Override
    public CommonResult<Payment> getPaymentById(long id) {
        return null;
    }

    @Override
    public CommonResult save(Payment payment) {
        return null;
    }

    @Override
    public String paymentFeignTimeout() {
        return null;
    }

    @Override
    public String paymentInfoOk(Integer id) {
        return "feign接口产生问题-paymentInfoOk";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "feign接口产生问题-paymentInfoTimeOut";
    }

    @Override
    public String paymentCircuitBreaker(Integer id) {
        return "feign接口产生问题-paymentCircuitBreaker";
    }
}