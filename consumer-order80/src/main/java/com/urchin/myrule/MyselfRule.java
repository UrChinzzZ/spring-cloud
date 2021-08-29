package com.urchin.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME evolution
 * @create 2021-05-25 22:30
 */
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        //随机算法
        return new RandomRule();
    }
}