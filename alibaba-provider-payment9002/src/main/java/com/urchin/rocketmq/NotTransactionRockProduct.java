//package com.urchin.rocketmq;
//
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.concurrent.TimeUnit;
//
///**
// * @ClassName RockProduct
// * @Description: 非事务消息队列配置类
// * @Author urchin
// * @Date 2022/1/11
// * @Version V1.0
// **/
//@Component
//public class NotTransactionRockProduct {
//    /**
//     *    MQ地址
//     */
//    @Value("${rocketmq.producer.namesrvAddr}")
//    private String  namesrvAddr;
//    /**
//     *    MQ生产者的组名
//     */
//    @Value("${rocketmq.producer.producerGroup}")
//    private String producerGroup;
//
//    private DefaultMQProducer producer;
//    /**
//     *    调用方注入MsgProducer后 通过这个方法获取配置好的DefaultMQProducer
//     */
//    public DefaultMQProducer  getProducer(){
//        return producer;
//    }
//    @PostConstruct
//    public  void initMQ(){
//        producer=new DefaultMQProducer(producerGroup);
//        producer.setNamesrvAddr(namesrvAddr);
//        producer.setVipChannelEnabled(false);
//        producer.setInstanceName(TimeUnit.MILLISECONDS.toString());
//        try {
//            producer.start();
//        }  catch (MQClientException e) {
//            e.printStackTrace();
//        }
//    }
//    @PreDestroy
//    public void destory(){
//        producer.shutdown();
//    }
//}
