package cn.jason.eurekaconsumer.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RibbonHelloController
 * @Author: Jason
 * @Date: 2020/2/19 21:04
 * @Description: TODO 使用Spring Cloud提供的负载均衡器（Ribbon）客户端接口来实现服务的消费
 */
@RequestMapping("/RibbonHello")
@RestController
public class RibbonHelloController {

//    @Resource(name="RibbonRestTemplate")
    @Autowired
    @Qualifier("RibbonRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String hello(@RequestParam String name) {
        name += "!《Ribbon》";
        /*Spring Cloud Ribbon 有一个拦截器，它能够在这里进行实际调用的时候，自动的去选取服务实例，
        并将这里的服务名替换成实际要请求的 IP 地址和端口，从而完成服务接口的调用。*/
        String url = "http://eureka-producer/hello/?name=" + name;
        return restTemplate.getForObject(url, String.class);
    }

}
