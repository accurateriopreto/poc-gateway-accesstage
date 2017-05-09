package br.com.accesstage.rest.v1.controller;

import br.com.accesstage.model.entity.Pedido;
import br.com.accesstage.model.repository.PedidoRepository;
import br.com.accesstage.rest.v1.mapper.PedidoRequestMapper;
import br.com.accesstage.rest.v1.vo.TransactionRequestVO;
import br.com.accesstage.rest.v1.vo.TransactionResponseVO;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caio
 */
@RestController
@RequestMapping("/operations")
public class OperationsController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoRequestMapper pedidoRequestMapper;

    private static final String[] PAYMENT_STATUS = {"pago", "recusado"};

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TransactionResponseVO> transaction(@RequestBody TransactionRequestVO body) {
        try {
            validateRequest(body);
            
            int statusIndex = 0 + (int) (Math.random() * 2);
            int authCode = 1000 + (int) (Math.random() * 999999);

            Pedido pedido = pedidoRequestMapper.mapToEntity(body);
            pedido.setAutorizacao(Integer.toString(authCode));
            pedido.setStatus(PAYMENT_STATUS[statusIndex]);

            Pedido save = pedidoRepository.save(pedido);

            TransactionResponseVO response = new TransactionResponseVO();
            response.setOrderStatus(PAYMENT_STATUS[statusIndex]);
            response.setAuthorizationCode(Integer.toString(authCode));
            response.setOrderNumber(body.getOrderNumber());
            response.setOrderTotal(body.getOrderTotal());
            response.setTransactionDate(new Date());

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (RuntimeException e){
            TransactionResponseVO respInvalid = new TransactionResponseVO();
            respInvalid.setOrderStatus(e.getMessage());
            return new ResponseEntity(respInvalid, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{codigoPedido}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<TransactionResponseVO> getByCodigoPedido(
            @PathVariable(value = "codigoPedido", required = true) String codigoPedido) {

        try {
            Pedido one = pedidoRepository.getOne(codigoPedido);
            return new ResponseEntity(pedidoRequestMapper.mapToVO(one), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            TransactionResponseVO responseNotFound = new TransactionResponseVO();
            responseNotFound.setOrderStatus("not_found");
            responseNotFound.setOrderNumber(codigoPedido);
            return new ResponseEntity(responseNotFound, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateRequest(TransactionRequestVO body) {
        if(body.getOrderNumber() == null || 
                (body.getOrderNumber() != null && body.getOrderNumber().isEmpty())){
            throw new RuntimeException("OrderNumber_cannot_be_null");
        }
        if(body.getOrderTotal() == null){
            throw new RuntimeException("OrderTotal_cannot_be_null");
        }
    }
}
