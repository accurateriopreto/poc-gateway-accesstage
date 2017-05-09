package br.com.accesstage.gateway;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class EurekaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EurekaApplication.class, args);
		new SpringApplicationBuilder(EurekaApplication.class)
        .web(true)
        .run(args);
	}
}
