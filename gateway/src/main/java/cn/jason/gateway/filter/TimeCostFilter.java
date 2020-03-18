package cn.jason.gateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * TimeCostFilter
 * 局部过滤器（实现GatewayFilter），需要单独配置进路由
 * 全局过滤器（实现GlobalFilter）
 * @author: Jason
 * @date: 2020/3/15
 */
public class TimeCostFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(GatewayFilter.class);
    private static final String TIME_BEGIN = "timeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // chain.filter(exchange) 之前的就是 “pre” 部分，之后的也就是 then 里边的是 “post” 部分
        // pre
        exchange.getAttributes().put(TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                // post
//                从Runable中创建Mono
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(TIME_BEGIN);
                    if (startTime != null) {
                        logger.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        // 设置最低优先级
        return Ordered.LOWEST_PRECEDENCE;
    }
}
