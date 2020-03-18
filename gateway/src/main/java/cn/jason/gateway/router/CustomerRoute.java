package cn.jason.gateway.router;

import cn.jason.gateway.filter.TokenFilter;
import cn.jason.gateway.limit.RateLimitByIpGatewayFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

/**
 * CustomerRoute
 *
 * @author: Jason
 * @date: 2020/3/15
 */
@SpringBootConfiguration
public class CustomerRoute {
    /**
     * customerRouteLocator  Java 的流式 API 进行路由的定义
     * @param builder:
     * @return: org.springframework.cloud.gateway.route.RouteLocator
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(r -> r.path("/fluent/customer/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new TokenFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://eureka-consumer")
                        .order(0)
                        .id("fluent_customer_service")
                )
                .route(r->r.path("/throttle/customer/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new RateLimitByIpGatewayFilter(10,1, Duration.ofSeconds(1))))
                        .uri("lb://eureka-consumer")
                        .order(0)
                        .id("throttle_customer_service"))
                .build();
        // @formatter:on
    }
}
