package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName:PaymentImpl
 * Package:
 * Description:
 * Date:2022/4/25 0025 19:38
 * author:szy
 */
@Service
public class PaymentImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment selectById(Long id) {
        return paymentDao.selectById(id);
    }
}
