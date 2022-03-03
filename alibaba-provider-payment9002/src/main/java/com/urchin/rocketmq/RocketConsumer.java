package com.urchin.rocketmq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @ClassName RocketConsumer
 * @Description: TODO 消息消费配置
 * @Author Hp
 * @Date 2022/1/11
 * @Version V1.0
 **/
@Component
public class RocketConsumer {
    @Value("${rocketmq.consumer.PushConsumer}")
    private String consumerGroup;

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    private DefaultMQPushConsumer consumer;
    @PostConstruct
    public void init() throws MQClientException {
        byte[] length = namesrvAddr.getBytes();
        System.out.println("字节"+length);
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        //设置consumer所订阅的Topic和Tag，*代表全部的Tag
        consumer.subscribe("test_topic_1", "*");
        /**
         * CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，跳过历史消息
         * CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
         *
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try{
                    System.out.println("接受:"+new String(msgs.get(0).getBody()));
                }catch (Exception e){
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
     @PreDestroy
     public void destory(){
     consumer.shutdown();
     }

}
