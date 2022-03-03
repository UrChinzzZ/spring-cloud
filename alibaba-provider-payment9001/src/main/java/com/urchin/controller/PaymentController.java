package com.urchin.controller;

import com.urchin.rocketmq.TransactionRockProduct;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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
    @Resource
    private TransactionRockProduct TransactionRockProduct;
    public  static HashMap<Long, Payment> hashMap =new HashMap<>();
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
    public CommonResult<Payment> paymentSQL(@PathVariable("id") long id) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Payment payment=hashMap.get(id);
        CommonResult<Payment> result=new CommonResult<Payment>(200,"from SQL payment server:"+serverPort,payment);
        String msg=result.getMessage();
        Message message=new Message("test_topic_1","test",msg.getBytes(StandardCharsets.UTF_8));
        SendResult MQResult=TransactionRockProduct.getProducer().send(message);
        return result;
    }
}