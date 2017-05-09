package br.com.accesstage.gateway.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayConsumerApplication.class, args);
	}
}
