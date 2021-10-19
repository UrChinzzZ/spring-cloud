package com.urchin.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.urchin.rabbitmq.util.RabbitMqUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-10-19 20:42
 */
public class ProducerWork1 {
    // 队列名称
    public static final String QUEUE_NAME="第二条队列";
    // 发送大量的消息
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel=RabbitMqUtils.getChannel();
        //声明队列
        /*
         * queueBind参数意义
         * 1.创建队列
         * 2.队列里的消息是持久化 默认情况 消息存储再内存中  持久化后储存再磁盘上
         * 3.该队列是否只提供一个消费者提供消费 是否进行消息共享 false 允许多个消费者消费  true 不允许多个消费
         * 4.是否自动删除 最后一个消费者端开连接以后 ，该队列是否自动删除 true 删除  反之不自动删除
         * 5. 其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //从控制台输入消息
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String message=scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(message+"消息已经发送");
        }
    }
}