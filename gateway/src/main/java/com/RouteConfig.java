package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Q版小李
 * @description
 * @create 2018/10/24 10:33
 */
@Configuration
public class RouteConfig {

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) -> {
            ServerHttpRequest request = serverWebExchange.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = serverWebExchange.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", "*");
                headers.add("Access-Control-Allow-Methods", "*");
                headers.add("Access-Control-Max-Age", "18000L");
                headers.add("Access-Control-Allow-Headers", "*");
                headers.add("Access-Control-Expose-Headers", "*");
                headers.add("Access-Control-Allow-Credentials", "true");
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return webFilterChain.filter(serverWebExchange);
        };
    }

}