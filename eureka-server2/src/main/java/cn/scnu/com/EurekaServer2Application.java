package cn.scnu.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//启动eurekaServer的注解
public class EurekaServer2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer2Application.class, args);
    }

}
