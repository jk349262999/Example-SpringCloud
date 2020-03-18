package cn.jason.gateway.router;

import cn.jason.gateway.GatewayApplocation;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {GatewayApplocation.class})
public class CustomerRouteTest {


    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void callMany() throws Exception {
        for (int i = 0; i < 20; i++) {
            try {
                call();
            } catch (HttpClientErrorException e) {
                if(e.getMessage() != null && e.getMessage().contains("429")){
                    System.out.println("没有资源了，等待1s");
                    Thread.sleep(1000);
                    call();
                }else{
                    e.printStackTrace();
                }
            }
        }
    }

    private void call() throws InterruptedException,HttpClientErrorException{
        String s = restTemplate.getForObject("http://localhost:14100/throttle/customer/FeignHello/index?name=jason&token=1", String.class);
        Thread.sleep(100);
        System.out.println(s);
    }
}