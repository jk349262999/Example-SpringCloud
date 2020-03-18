package cn.jason.gateway.limit;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * RemoteAddrKeyResolver
 *
 * @author: Jason
 * @date: 2020/3/18
 */
@CommonsLog
public class RemoteAddrKeyResolver implements KeyResolver {
    public static final String BEAN_NAME = "remoteAddrKeyResolver";
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        // uri: /FeignHello/index
        log.info("uri: "+exchange.getRequest().getURI().getPath());
        // Host: 0:0:0:0:0:0:0:1
        log.info("Host: "+exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        //或者使用uri exchange.getRequest().getURI().getPath()
    }

}
