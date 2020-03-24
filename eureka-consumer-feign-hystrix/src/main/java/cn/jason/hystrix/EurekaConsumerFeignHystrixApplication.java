package cn.jason.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * EurekaConsumerFeignHystrixApplication
 *
 * 为启动类添加 @EnableCircuitBreaker 或 @EnableHystrix 注解，开启断路器功能
 * @author: Jason
 * @date: 2020/3/10
 */
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class EurekaConsumerFeignHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeignHystrixApplication.class, args);
    }

}
