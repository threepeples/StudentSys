package cn.scnu.com.microservicecourse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.scnu.com.microservicecourse.mapper")
public class MicroserviceCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCourseApplication.class, args);
    }

}
