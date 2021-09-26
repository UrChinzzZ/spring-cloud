package com.urchin.rabbitmq.util.consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.urchin.rabbitmq.util.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author urchin
 * @Description 消费者线程
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-09-26 22:25
 */
public class Work01  {
    // 队列名称
    public static final String QUEUE_NAME="第二条队列";

    public static void main(String[] args) throws IOException, TimeoutException {
     Channel channel=RabbitMqUtils.getChannel();
        /*
         *接收消息
         * 1.消费那个队列
         *2。消费成功后 true是使用自动应答 false还是手动应答
         * 3.消费者成功消费，产生回调的消息
         * 4.消费者取消消费的回调 信息
         */
        //声明接口回调 成功时候的回调
        DeliverCallback deliverCallback=(consumerTag, message)->{
            System.out.println(new String(message.getBody()));
        };
        CancelCallback cancelCallback= consumerTag->{
            System.out.println("消息消费被取消，回调参数");
        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}