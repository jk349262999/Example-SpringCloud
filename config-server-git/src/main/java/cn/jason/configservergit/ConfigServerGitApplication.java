package cn.jason.configservergit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ClassName: ConfigServerApplication
 * @author: Jason
 * @Date: 2020/2/28 23:01
 * @Description: TODO  启动类
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerGitApplication.class, args);
    }
}
