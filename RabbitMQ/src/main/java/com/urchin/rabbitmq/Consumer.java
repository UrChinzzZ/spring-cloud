package com.urchin.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author urchin
 * @Description  MQ消费者
 * @PROJECT_NAME evolution
 * @create 2021-09-22 22:44
 */
public class Consumer {
    //队列名称
    public  static  final String Queue_name="第二条队列";
    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建工厂
        ConnectionFactory factory=new ConnectionFactory();
        //工厂IP  链接MQ队列
        factory.setHost("192.168.1.106");
        // 设置用户名
        factory.setUsername("urchin");
        //设置密码
        factory.setPassword("Tt971220");
        //创建链接
        Connection connection=factory.newConnection();
        // 获取信道
        Channel channel =connection.createChannel();
        /*
         *接收消息
         * 1.消费那个队列
         *2。消费成功后 true是使用自动应答 false还是手动应答
         * 3.消费者成功消费，产生回调的消息
         * 4.消费者取消消费的回调 信息
         */
        //声明接口回调 成功时候的回调
        DeliverCallback deliverCallback=(consumerTag,message)->{
            System.out.println(new String(message.getBody()));
        };
        CancelCallback cancelCallback=consumerTag->{
            System.out.println("消息消费被中断");
        };
        channel.basicConsume(Queue_name,true,deliverCallback,cancelCallback);

    }
}