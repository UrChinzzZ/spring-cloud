package com.urchin.cloud.filter;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author urchin
 * @Description 网关过滤器
 * @PROJECT_NAME provider-Hystrix-payment8001
 * @create 2021-08-29 16:17
 */
@Component
@Log4j2
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("come in MyLogGateWayFilter"+new Date());
       String username=exchange.getRequest().getQueryParams().getFirst("username");
        if (username==null)
        {
            log.warn("用户名为空");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return  exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
    /**
    加载过滤器顺序  数字越小 优先级越高
    */
    @Override
    public int getOrder() {
        return 0;
    }
}