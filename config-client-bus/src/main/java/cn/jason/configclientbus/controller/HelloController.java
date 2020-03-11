package cn.jason.configclientbus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author: Jason
 * @date: 2020/3/11
 */
@RefreshScope
@RestController
public class HelloController {

    @Value("${neo}")
    private String profile;

    @GetMapping("/info")
    public String hello() {
        return profile;
    }
}
