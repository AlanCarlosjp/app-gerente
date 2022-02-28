package com.eurekserver.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekServerMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekServerMsApplication.class, args);
    }

}
