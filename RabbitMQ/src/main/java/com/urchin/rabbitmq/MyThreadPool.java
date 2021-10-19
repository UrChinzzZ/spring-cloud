package com.urchin.rabbitmq;


import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author urchin
 * @Description  线程池
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-10-19 21:04
 */
public class MyThreadPool {
    /*
     * 默认线程数
     */
    private  static int WORK_NUM=5;
    /*
     *队列任务数
     */
    private  static int TASK_COUNT=100;
    /**
        工作线程
     */
    private WorkThread[] workThreads;
    /**
     任务队列 缓冲
     */
    private final BlockingQueue<Object> taskQueue;
    /*
     *
     */
    private  int worker_num;
    /**
    创建默认线程池
     */
    public MyThreadPool() {
        this(WORK_NUM,TASK_COUNT);
    }

    public MyThreadPool(int worker_num, int taskCount) {
        if (worker_num<=0){
            worker_num=WORK_NUM;
        }
        if (taskCount<=0){
            taskCount=TASK_COUNT;
        }
        /*
        用户启动线程数
     */
        taskQueue =new ArrayBlockingQueue<Object>(taskCount);
        workThreads=new WorkThread[worker_num];
        for (int i=0;i<worker_num;i++){
            workThreads[i]=new WorkThread();
            workThreads[i].start();
        }
    }
    /*
          执行任务
     */
    public void execute(Readable task){
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
        销毁线程池
     */
    public void destory(){
        //
        System.out.println("ready close pool....");
        for (int i=0;i<worker_num;i++){
            workThreads[i].stopWorker();
            workThreads[i]=null;
        }
        taskQueue.clear();
    }

    @Override
    public String toString() {
        return "MyThreadPool{" +
                "workThreads=" + Arrays.toString(workThreads) +
                ", takeQuece=" + taskQueue.size() +
                ", worker_num=" + worker_num +
                '}';
    }

    /*
        内部 工作线程类
         */
    private class WorkThread extends Thread{
        @Override
        public void run() {
            Runnable r = null;
            while (!isInterrupted()){
                try {
                    r= (Runnable) taskQueue.take();
                    if (r!=null){
                        System.out.println(getId()+"ready exec:"+r);
                        r.run();
                    }
                    r =null;
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        public void stopWorker() {
            interrupt();
        }
    }
}