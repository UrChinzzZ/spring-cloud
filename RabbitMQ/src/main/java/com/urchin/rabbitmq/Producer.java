package com.urchin.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author urchin
 * @Description 消息生产者
 * @PROJECT_NAME evolution
 * @create 2021-09-14 22:54
 */
public class Producer {
    // 队列名称
    public static final String QUEUE_NAME="第二条队列";
    // 发送消息
    public static void main(String[] args) throws IOException, TimeoutException {
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
        Channel channel =connection.createChannel();
        /*
         * queueBind参数意义
         * 1.创建队列
         * 2.队列里的消息是持久化 默认情况 消息存储再内存中  持久化后储存再磁盘上
         * 3.该队列是否只提供一个消费者提供消费 是否进行消息共享 false 允许多个消费者消费  true 不允许多个消费
         * 4.是否自动删除 最后一个消费者端开连接以后 ，该队列是否自动删除 true 删除  反之不自动删除
         * 5. 其他参数
        */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息
        String message="hallo MQ";
        /*
         *1.发送到那个交换机
         *2。路由配置
         * 3.路由的Key值
         * 4.发送的消息  消息需要二进制
         */
        String urchin = "";

        channel.basicPublish(urchin,QUEUE_NAME,null,message.getBytes());
        System.out.println("消息已经发送");
    }
}