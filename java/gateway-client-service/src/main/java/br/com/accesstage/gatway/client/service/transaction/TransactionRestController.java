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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController {
	
	private final Logger LOG = Logger.getLogger(TransactionRestController.class.getName());
	
	@Value("${gateway.endpoint.post}")
	private String urlPostEndPoint;
	
	@RequestMapping(method = RequestMethod.POST,
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResponse> newTransaction(@RequestBody TransactionRequest request){
		
		LOG.info("INICIO TRANSACTION LOG");
		
		RestTemplate restTemplate = new RestTemplate();
		TransactionResponse transactionsResponse = restTemplate.postForObject(urlPostEndPoint, request,TransactionResponse.class);
		
		return ResponseEntity.ok(transactionsResponse);
	}
        
	@RequestMapping(value = "/{codigoPedido}",
                method = RequestMethod.GET,
                produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionResponse> getById(@PathVariable(value = "codigoPedido", required = true) String codigoPedido){
		
		LOG.info("INICIO GET LOG");
		
		RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<TransactionResponse> transactionsResponse = restTemplate.getForEntity(urlPostEndPoint+"/"+codigoPedido, TransactionResponse.class);
		
		return ResponseEntity.ok(transactionsResponse.getBody());
	}        
}
