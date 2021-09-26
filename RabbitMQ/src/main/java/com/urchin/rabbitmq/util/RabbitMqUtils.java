package com.urchin.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-09-26 22:18
 */
public class RabbitMqUtils {
    public static Channel getChannel() throws IOException, TimeoutException {
        //创建工厂
        ConnectionFactory factory=new ConnectionFactory();
        //工厂IP  链接MQ队列
        factory.setHost("192.168.1.106");
        //设置端口
        factory.setPort(Integer.parseInt("5672"));
        // 设置用户名
        factory.setUsername("urchin");
        //设置密码
        factory.setPassword("Tt971220");
        //创建链接
        Connection connection=factory.newConnection();
        // 获取信道
        return connection.createChannel();
    }
}