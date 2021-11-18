package urchin.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME
 * @create 2021-11-08 21:33
 */
@RestController
public class FlowLimitController {
    @GetMapping("testA")
    public String testA(){
        return  "---------------------testA" ;
    };
    @GetMapping("testB")
    public String testB(){
        return  "---------------------testB" ;
    };
}