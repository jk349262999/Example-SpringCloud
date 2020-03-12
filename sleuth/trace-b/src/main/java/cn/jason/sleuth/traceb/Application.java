package cn.jason.sleuth.traceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * cn.jason.sleuth.traceb.Application
 *
 * @author: Jason
 * @date: 2020/3/13
 */
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/trace-b")
    public String trace() {
        System.out.println("===call trace-b===");
        return "Trace";
    }
}
