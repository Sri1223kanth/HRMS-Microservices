package com.Workday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class WorkdayCloneApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                com.Workday.WorkdayCloneApplication.class,
                args
        );
    }
}