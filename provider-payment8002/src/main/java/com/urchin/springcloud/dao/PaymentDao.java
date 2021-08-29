package com.urchin.springcloud.dao;

import com.urchin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-16 21:37
 */
@Mapper
public interface PaymentDao {
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
* @Description:  * @param null
* @param id:  主键修改
* @return: Payment 实体
* @author Mr.urchin
* @Date: 2021/5/16
*/
    public Payment getPaymentById(@Param("id") long id);
}