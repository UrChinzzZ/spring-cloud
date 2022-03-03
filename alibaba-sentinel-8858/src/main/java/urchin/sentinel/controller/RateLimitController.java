package urchin.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.urchin.springcloud.entities.CommonResult;
import com.urchin.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import urchin.sentinel.myHandler.CustomerBlockHandler;

/**
 * ClassName: RateLimitController <br/>
 * Description: <br/>
 * date: 2021/11/18 22:42<br/>
 *
 * @author <br/>
 * @since JDK 1.8
 */
@RestController
public class RateLimitController {
@GetMapping("/byResource")
@SentinelResource(value = "byResource",blockHandler = "handleException",fallback = "handleException")
    public CommonResult byResource(){
        return  new CommonResult(444,"按照资源名称限流Ok",new Payment(2020L,"serial001"));
}
    public CommonResult handleException(BlockException exception){

        return  new CommonResult(200,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }
    @GetMapping(value = "/byUrl")
    @SentinelResource(value ="byUrl")
    public  CommonResult byUrl(){
    return new CommonResult(200,"按照URL进行限流测试OK",new Payment(2020,"测试11.21"));
    }
    @GetMapping(value = "/customerBlockHandler")
    @SentinelResource(value ="customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    public  CommonResult customerBlockHandler(){
        return new CommonResult(200,"按自定义熔断方法OK",new Payment(2020,"测试11.21"));
    }
}
