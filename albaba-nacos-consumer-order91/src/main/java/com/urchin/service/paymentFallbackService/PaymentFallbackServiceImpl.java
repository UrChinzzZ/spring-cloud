package com.urchin.service.paymentFallbackService;

import com.urchin.service.paymentFeignService.PaymentFeignService;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

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
}