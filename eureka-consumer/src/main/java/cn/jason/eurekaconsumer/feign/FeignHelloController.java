package cn.jason.eurekaconsumer.feign;

import cn.jason.eurekaconsumer.feign.service.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FeignHelloController
 * @author: Jason
 * @Date: 2020/2/19 22:30
 * @Description: TODO 使用Spring Cloud提供的负载均衡器（Feign）客户端接口来实现服务的消费 feign其实是集成了Ribbon
 */
@RequestMapping("FeignHello")
@RestController
public class FeignHelloController {

    @Autowired
    HelloRemote helloRemote;

    @GetMapping("/index")
    public String index(@RequestParam String name) throws InterruptedException {
        return helloRemote.hello(name + "!《Feign》");
    }
}
