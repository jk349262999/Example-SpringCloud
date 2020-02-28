package cn.jason.hystrix.service.impl;

import cn.jason.hystrix.service.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: HelloRemoteHystrix
 * @Author: Jason
 * @Date: 2020/2/27 03:27
 * @Description: TODO
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(@RequestParam(value = "name")String name) {
        return "fallback触发";
    }
}
