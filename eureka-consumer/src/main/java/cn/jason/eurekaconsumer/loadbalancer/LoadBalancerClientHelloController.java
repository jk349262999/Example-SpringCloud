package cn.jason.eurekaconsumer.loadbalancer;

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
 * @ClassName: LoadBalancerClientHelloController
 * @author: Jason
 * @Date: 2020/2/19 21:04
 * @Description: TODO 使用Spring Cloud提供的负载均衡器（LoadBalancerClient）客户端接口来实现服务的消费
 */
@RequestMapping("/LBCHello")
@RestController
public class LoadBalancerClientHelloController {

    @Autowired
    private LoadBalancerClient client;

//    @Resource(name="LBCRestTemplate")

    @Autowired
    @Qualifier("lbcRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public String hello(@RequestParam String name) {
        name += "!《LBC》";
        ServiceInstance instance = client.choose("eureka-producer");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello/index?name=" + name;
        return restTemplate.getForObject(url, String.class);
    }

}
