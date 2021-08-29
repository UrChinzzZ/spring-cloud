package com.urchin.springcloud.service;

import com.urchin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-16 22:42
 */
@Service
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
     */    /**
     * fetch data by rule id
     * @Description:  超时访问  模拟出错
     * @param id:  参数
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/18
     */
    public Payment getPaymentById( long id);
    /**
     * fetch data by rule id
     * @Description: 正常访问
     * @param id:  参数
     * @return:
     * @author Mr.urchin
     * @Date: 2021/8/18
     */
    public String paymentInfoOk( Integer id);

    public String paymentInfoTimeOut( Integer id);
    /**
     * fetch data by rule id
     * @Description:  * 服务熔断方法
     * @Param:  Integer
     * @return:  String
     * @author Mr.urchin
     * @Date: 2021/8/26
     */
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);

}