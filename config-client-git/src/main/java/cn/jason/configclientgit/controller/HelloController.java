package cn.jason.configclientgit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @author: Jason
 * @Date: 2020/2/29 00:06
 * @Description: TODO
 * 加上 @RefreshScope，post执行 /actuator/refresh 的时候就会更新此类下面的变量值
 * curl -X POST http://localhost:13000/actuator/refresh
 * 这样很麻烦，每次都得手动刷新，可以通过集成Bus来自动刷新
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
