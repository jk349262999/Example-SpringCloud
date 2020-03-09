package cn.jason.eurekaconsumer.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HelloRemote
 *
 * @author: Jason
 * @Date: 2020/3/9
 */
@FeignClient(name = "eureka-producer")
public interface HelloRemote {

    /**
     * hello
     * @param name:
     * @return: java.lang.String
     */
    @GetMapping("/hello/")
    String hello(@RequestParam(value = "name") String name);
}
