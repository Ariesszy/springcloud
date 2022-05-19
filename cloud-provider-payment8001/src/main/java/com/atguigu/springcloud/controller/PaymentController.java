package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:PaymentController
 * Package:
 * Description:
 * Date:2022/4/25 0025 19:39
 * author:szy
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/payment/create")
    public CommonResult<Integer> creat(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****添加结果:"+result);
        if (result > 0) {
            return new CommonResult<>(200, "添加成功", result);
        } else {
            return new CommonResult<>(444,"添加失败",null);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> select(@PathVariable("id") Long id){
        Payment payment = paymentService.selectById(id);
        log.info("****查找结果:"+payment);
        if (payment==null){
            return new CommonResult<>(444,"没有找到",null);
        }else {
            return new CommonResult<>(200,"查询成功"+"\t 服务端口："+serverPort,payment);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("****element"+element);
        }
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance : serviceInstances) {
            log.info(serviceInstance.getInstanceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }
}
