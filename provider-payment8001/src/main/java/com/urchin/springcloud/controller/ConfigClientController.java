package com.urchin.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author urchin
 * @Description 获取配置中心配置参数
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-08-29 22:04
 */
//@RestController
//@RequestMapping("configController")
//@RefreshScope
//public class ConfigClientController {
// @Value("${config.info}")
//    private  String configInfo;
// @GetMapping("/configInfo")
//    public String getConfigInfo()
//     {
//         return configInfo;
//     }
//}