package com.urchin.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author urchin
 * @Description 手写ribbon轮询算法
 * @PROJECT_NAME evolution
 * @create 2021-05-25 23:00
 */
public interface LoadBalancer {
    //获取euerka服务 服务数量
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}