package com.urchin.cloud.config;

import java.time.ZonedDateTime;

/**
 * @author urchin
 * @Description 获取当前时间
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-08-29 16:04
 */

public class GetDateTimeConfig {
    public  static  void main(String[] args){
        ZonedDateTime zonedDateTime=ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
 }