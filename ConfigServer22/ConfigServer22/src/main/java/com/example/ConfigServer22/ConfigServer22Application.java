package com.example.ConfigServer22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer22Application {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServer22Application.class, args);
	}

}
