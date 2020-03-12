package cn.jason.sleuth.tracea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * cn.jason.sleuth.tracea.Application
 *
 * @author: Jason
 * @date: 2020/3/13
 */
@RestController
@SpringBootApplication
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    TracebRemote tracebRemote;

    @GetMapping("/trace-a")
    public String trace() {
        System.out.println("===call trace-a===");
        return tracebRemote.traceB();
//        return webClient().get()
//                .uri("/trace-b")
//                .retrieve()
//                .bodyToMono(String.class);
    }
}
