package cn.jason.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: EurekaConsumerFeignHystrixApplication
 * @Author: Jason
 * @Date: 2020/2/27 2:32
 * @Description: TODO
 */
// 为启动类添加 @EnableCircuitBreaker 或 @EnableHystrix 注解，开启断路器功能
@EnableHystrix
// end
@EnableFeignClients
@SpringBootApplication
public class EurekaConsumerFeignHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeignHystrixApplication.class, args);
    }

}
