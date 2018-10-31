package com.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: Lucifer
 * @date: 2018/10/25 16:11
 * @description:
 */
@Slf4j
@Component
public class ExecuteTimeFilter implements GlobalFilter {

    private static final String START_TIME = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            if (exchange.getRequest().getRemoteAddress() != null) {
                log.info("客户端请求ip：" + exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
            }
            log.info("请求路径：" + exchange.getRequest().getURI());
            log.info("请求方式：" + exchange.getRequest().getMethod());
            log.info("token令牌：" + exchange.getRequest().getHeaders().getFirst("token"));
            log.info("请求参数：" + exchange.getRequest().getQueryParams());
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                long executeTime = (System.currentTimeMillis() - startTime);
                log.info("执行时间：" + executeTime + "ms\n");
            }
        }));
    }

}
