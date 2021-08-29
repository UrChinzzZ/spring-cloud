package com.urchin.springcloud.service.impl;

import com.urchin.springcloud.dao.PaymentDao;
import com.urchin.springcloud.entities.Payment;
import com.urchin.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-16 22:44
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    final PaymentDao paymentDao;
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
    @Override
    public int save( Payment payment) {
        return paymentDao.save(payment);
    }
    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}