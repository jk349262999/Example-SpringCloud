package cn.jason.eurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: EurekaProducerApplication
 * @Author: Jason
 * @Date: 2020/2/19 19:12
 * @Description: TODO
 */
@EnableFeignClients
@SpringBootApplication
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

    /**
     *  LoadBalancerClient方式需引用
     * @return
     */
    @Bean
    public RestTemplate LBCRestTemplate() {
        return new RestTemplate();
    }

    /**
     *  Ribbon方式需引用
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate RibbonRestTemplate() {
        return new RestTemplate();
    }



}
