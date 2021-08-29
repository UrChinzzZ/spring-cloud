package com.urchin.springcloud.lb.impl;

import com.urchin.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-25 23:05
 */
@Component
public class LoadBalancerImpl implements LoadBalancer {
    //创建
    private AtomicInteger atomicInteger=new AtomicInteger();

    public  final  int getAndIncrement(){
        int current;
        int next;
        do {
            current=this.atomicInteger.get();
            next=current>2147483647?0:current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("第几次访问next***"+next);
        return next;
    }
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index=getAndIncrement()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}