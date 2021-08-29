package com.urchin.springcloud.service;

import com.urchin.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-16 22:42
 */
public interface PaymentService {
    /**
     * fetch data by rule id
     * @Description: 新增
     * @param  payment:实体
     * @return  int 查询
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    public int save( Payment payment);
    /**
     * fetch data by rule id
     * @Description:  *主键查询
     * @param id:  主键
     * @return: Payment 实体
     * @author Mr.urchin
     * @Date: 2021/5/16
     */
    public Payment getPaymentById(long id);
}