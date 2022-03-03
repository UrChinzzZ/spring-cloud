package urchin.sentinel.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.urchin.springcloud.entities.CommonResult;

/**
 * ClassName: customerBlockHandler <br/>
 * Description: sentinel监控 熔断方法处理类<br/>
 * date: 2021/11/21 20:25<br/>
 *
 * @author <br/>
 * @since JDK 1.8
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(4444,"按自定义熔断方法启动=====》handlerException");
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(4444,"按自定义熔断方法启动=====》handlerException2");
    }
}
