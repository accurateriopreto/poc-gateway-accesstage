package br.com.accesstage.rest.v1.controller;

import br.com.accesstage.rest.v1.vo.TransactionRequestVO;
import br.com.accesstage.rest.v1.vo.TransactionResponseVO;
import java.util.Date;
import java.util.Random;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caio
 */
@RestController()
public class OperationsController {
    private static final String[] PAYMENT_STATUS = {"pago", "recusado"};

    @RequestMapping(method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponseVO transaction(@RequestBody TransactionRequestVO body){
        
        int statusIndex = 0 + (int)(Math.random() * 2); 
        int authCode = 1000 + (int)(Math.random() * 999999);
        
        TransactionResponseVO response = new TransactionResponseVO();
        response.setOrderStatus(PAYMENT_STATUS[statusIndex]);
        response.setAuthorizationCode(Integer.toString(authCode));
        response.setOrderNumber(body.getOrderNumber());
        response.setOrderTotal(body.getOrderTotal());
        response.setTransactionDate(new Date());        
        
        return response;
    }
}
