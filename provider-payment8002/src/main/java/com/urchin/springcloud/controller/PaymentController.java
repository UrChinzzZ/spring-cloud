package com.urchin.springcloud.controller;

import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import com.urchin.springcloud.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-16 22:51
 */
@RestController

public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    @Resource
    DiscoveryClient discoveryClient;
            final  PaymentService paymentService;
    private static org.apache.logging.log4j.Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    /**
     * fetch data by rule id
     * @Description: 新增
     * @param  payment:实体
     * @return  int 查询
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    @RequestMapping(value = "payment/create", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ResponseBody
    public CommonResult save(@RequestBody Payment payment){
        Integer result=paymentService.save(payment);
       if(result>0){
           return new CommonResult(200,"插入成功"+serverPort);
         }else {
           return new CommonResult(444,"插入失败");
        }

    }
    /**
     * fetch data by rule id
     * @Description:  *主键查询
     * @param id:  主键
     * @return: Payment 实体
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment=paymentService.getPaymentById(id);
        if(payment !=null){
            return new CommonResult(200,"查询成功"+serverPort,payment);
        }else {
            return new CommonResult(555,"没有记录"+id,null
            );
        }
    };
    /**
     * fetch data by rule id
    * @Description: 服务发现
    * @Param:
    * @return:
    * @author Mr.urchin
    * @Date: 2021/5/19
    */
    @GetMapping(value = "/payment/discovery")
    public  Object discovery(){
        List<String> services=discoveryClient.getServices();
        for (String element: services) {
            logger.info("===="+element);
        }
        List<ServiceInstance> serviceInstances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: serviceInstances) {
            logger.info("===="+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return  this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}