package com.urchin.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * @ClassName RockProduct
 * @Description: 事务消息队列配置类
 * @Author urchin
 * @Date 2022/1/11
 * @Version V1.0
 **/
@Component
public class TransactionRockProduct {
    /**
     *    MQ地址
     */
    @Value("${rocketmq.producer.namesrvAddr}")
    private String  namesrvAddr;
    /**
     *    MQ生产者的组名
     */
    @Value("${rocketmq.producer.producerGroup}")
    private String producerGroup;

    private TransactionMQProducer  producer;
    /**
     *    调用方注入MsgProducer后 通过这个方法获取配置好的DefaultMQProducer
     */
    public DefaultMQProducer  getProducer(){
        return producer;
    }
    @PostConstruct
    public  void initMQ(){
        producer=new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setExecutorService(executorService);
        producer.setTransactionListener(new TransactionListenerImpl());

        try {
            producer.start();
        }  catch (MQClientException e) {
            e.printStackTrace();
        }
    }
    /**
     * 定义一个线程池 让broker用来执行回调和回查
     *
     * @return
     */
    ExecutorService executorService = new ThreadPoolExecutor(2,5,100,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2000),
            new ThreadFactory(){
                @Override
                public Thread newThread(Runnable r){
                    Thread thread = new Thread(r);
                    thread.setName("urchin_producer_group_name"+"check-thread"); //一般线程都要起名字，方便排错日志查询等
                    return thread;
                }
            });
    /**
     * 事务监听
     */
    class TransactionListenerImpl implements TransactionListener{
        /**
         * 第一次判断是否提交或回滚
         *
         * @param msg
         * @param arg
         * @return
         */
        @Override
        public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            //message就是那个半发送的消息 arg是在transcationProducter.send(Message,Object)时的另外一个携带参数）
            //执行本地事务或调用其他为服务
//            if(true){
//                return LocalTransactionState.COMMIT_MESSAGE;
//            }
//            if(true){
//                return LocalTransactionState.ROLLBACK_MESSAGE;
//            }
            //如果在检查事务时数据库出现宕机可以让broker过一段时间回查 和return null 效果相同
            return LocalTransactionState.UNKNOW;
        }
        /**
         * 返回UNKOWN时回查！
         * @param msg
         * @return
         */
        @Override
        public LocalTransactionState checkLocalTransaction(MessageExt msg) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }
    }
    @PreDestroy
    public void destory(){
        producer.shutdown();
    }

}
