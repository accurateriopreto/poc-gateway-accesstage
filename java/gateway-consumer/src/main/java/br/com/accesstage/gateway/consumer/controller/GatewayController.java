package br.com.accesstage.gateway.consumer.controller;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.accesstage.gateway.consumer.configuration.Configuration;
import br.com.accesstage.gateway.consumer.vo.ConsumerTransactionRequest;
import br.com.accesstage.gateway.consumer.vo.ConsumerTransactionResponse;

@RestController
@RibbonClient(name="gateway-client-service",configuration=Configuration.class)
@EnableDiscoveryClient
public class GatewayController  {
	
	private static final Logger LOG = Logger.getLogger(GatewayController.class.getName());
	
	@Value("${server.url}")
	private String url;
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	 RestTemplate restTemplate;
	
	@RequestMapping(value="/transactions",method=RequestMethod.POST)
	public ResponseEntity<?> transactions(@RequestBody ConsumerTransactionRequest consumerTransactionRequest) {
		
		LOG.info("NOVA TRANSACAO CONSUMER");
		
		consumerTransactionRequest.setOrderNumber(UUID.randomUUID().toString());
		String urlConcatenada = url.concat("transactions");
		ConsumerTransactionResponse consumerTransactionResponse =restTemplate.postForObject(urlConcatenada, consumerTransactionRequest, ConsumerTransactionResponse.class);
		return ResponseEntity.ok(consumerTransactionResponse);
	}
}
