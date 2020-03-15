package cn.jason.hystrix.service;

import cn.jason.hystrix.service.impl.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HelloRemote
 * 
 * @author: Jason
 * @date: 2020/3/10
 */
@Component
@FeignClient(name = "eureka-producer",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
    
    /**
     * hello
     * @param name: 
     * @return: java.lang.String
     */
    @GetMapping("/hello/index")
    String hello(@RequestParam(value = "name") String name);
}
