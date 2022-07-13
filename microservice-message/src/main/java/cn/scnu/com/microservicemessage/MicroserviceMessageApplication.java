package cn.scnu.com.microservicemessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMessageApplication.class, args);
    }

}
