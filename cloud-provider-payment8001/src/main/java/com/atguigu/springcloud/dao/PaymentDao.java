package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:PaymentDao
 * Package:
 * Description:
 * Date:2022/4/25 0025 19:22
 * author:szy
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);
    Payment selectById(Long id);
}
