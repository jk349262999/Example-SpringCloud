package cn.jason.configserverbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ConfigServerBus
 *
 * @author: Jason
 * @date: 2020/3/10
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerBusApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerBusApplication.class, args);
    }
}
