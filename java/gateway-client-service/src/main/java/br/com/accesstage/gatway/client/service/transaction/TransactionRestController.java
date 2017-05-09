package br.com.accesstage.gatway.client.service.transaction;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.accesstage.gatway.client.service.transaction.vo.TransactionRequest;
import br.com.accesstage.gatway.client.service.transaction.vo.TransactionResponse;

@RestController
public class TransactionRestController {
	
	private final Logger LOG = Logger.getLogger(TransactionRestController.class.getName());
	
	@Value("${gateway.endpoint.post}")
	private String urlPostEndPoint;
	
	@RequestMapping(value="/transactions",method = RequestMethod.POST)
	public ResponseEntity<TransactionResponse> newTransaction(@RequestBody TransactionRequest request){
		
		LOG.info("INICIO TRANSACTION LOG");
		
		RestTemplate restTemplate = new RestTemplate();
		TransactionResponse transactionsResponse = restTemplate.postForObject(urlPostEndPoint, request,TransactionResponse.class);
		
		return ResponseEntity.ok(transactionsResponse);
	}
}
