package com.api.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApiPedidoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPedidoApplication.class, args);
    }

}
