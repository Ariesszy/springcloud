package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * ClassName:LoadBalancer
 * Package:
 * Description:
 * Date:2022/5/10 0010 14:38
 * author:szy
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
