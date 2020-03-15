package cn.jason.eurekaproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: HelloController
 * @author: Jason
 * @Date: 2020/2/19 19:13
 * @Description: TODO
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    /**
     * 集群使用判断子服务
     */
    @Value("${config.producer.instance:0}")
    private int instance;

    @GetMapping("/index")
    public String hello(@RequestParam String name) {
        return "[" + instance + "]" + "Hello, " + name + ", I am  eureka-producer, " + new Date();
    }
}
