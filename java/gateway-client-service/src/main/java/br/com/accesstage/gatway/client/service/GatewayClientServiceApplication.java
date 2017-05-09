package br.com.accesstage.gatway.client.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
public class GatewayClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayClientServiceApplication.class, args);
	}
}
