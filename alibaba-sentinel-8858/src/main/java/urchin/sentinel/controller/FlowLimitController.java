package urchin.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author urchin
 * @Description
 * @PROJECT_NAME
 * @create 2021-11-08 21:33
 */
@RestController
@Log4j2
public class FlowLimitController {
    @GetMapping("testA")
    public String testA(){
        return  "---------------------testA" ;
    };
    @GetMapping("testB")
    public String testB(){
        return  "---------------------testB" ;
    };
    @GetMapping("testC")
    public String testC(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testC 测试");
        return  "---------------------testC" ;
    };
    @RequestMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2
                             ){
        log.info("testHotKey 测试");
        return  "---------------------testHotKey" ;
    };
    public String deal_testHotKey(String p1,String p2, BlockException exception){
        return "------------deal_testHotKey 我是熔断兜底方法";
    }
}