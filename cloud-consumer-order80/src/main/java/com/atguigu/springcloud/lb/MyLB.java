package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:MyLB
 * Package:
 * Description:
 * Date:2022/5/10 0010 14:40
 * author:szy
 */
@Component
public class MyLB implements LoadBalancer{
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current=this.atomicInteger.get();
            next=current>2147483647?0:current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****next: "+next);
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index=getAndIncrement()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
