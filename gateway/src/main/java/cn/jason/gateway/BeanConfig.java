package cn.jason.gateway;

import cn.jason.gateway.filter.PrintParamsGatewayFilterFactory;
import cn.jason.gateway.filter.TimeCostFilter;
import cn.jason.gateway.limit.RemoteAddrKeyResolver;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * FilterConfig
 *
 * @author: Jason
 * @date: 2020/3/15
 */
@SpringBootConfiguration
public class BeanConfig {

    @Bean
    public TimeCostFilter timeCostFilter(){
        return new TimeCostFilter();
    }

    @Bean
    public PrintParamsGatewayFilterFactory printParamsGatewayFilterFactory(){
        return new PrintParamsGatewayFilterFactory();
    }

    @Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }
}
