package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * ClassName:PaymentService
 * Package:
 * Description:
 * Date:2022/4/25 0025 19:35
 * author:szy
 */
public interface PaymentService {
    int create(Payment payment);
    Payment selectById(Long id);
}
