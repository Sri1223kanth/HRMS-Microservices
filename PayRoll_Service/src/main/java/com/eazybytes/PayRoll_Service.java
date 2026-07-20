package com.eazybytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PayRoll_Service {

    public static void main(String[] args) {

        SpringApplication.run(
                PayRoll_Service.class,
                args
        );
    }
}