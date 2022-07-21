package cn.scnu.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.scnu.com.mapper")
public class MicroserviceMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMessageApplication.class, args);
    }

}
