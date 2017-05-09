/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.accesstage.rest.v1.mapper;

import br.com.accesstage.model.entity.Pedido;
import br.com.accesstage.rest.v1.vo.TransactionRequestVO;
import br.com.accesstage.rest.v1.vo.TransactionResponseVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author caio
 */
@Component
public class PedidoRequestMapper {
    public Pedido mapToEntity(TransactionRequestVO request){
        
        if(request != null){
            Pedido pedido = new Pedido();
            pedido.setCodigo(request.getOrderNumber());
            pedido.setValor(request.getOrderTotal());
            return pedido;
        }
        return null;
    }
    
    public TransactionResponseVO mapToVO(Pedido pedido){
        if(pedido != null){
            TransactionResponseVO response = new TransactionResponseVO();
            response.setAuthorizationCode(pedido.getAutorizacao());
            response.setOrderNumber(pedido.getCodigo());
            response.setOrderStatus(pedido.getStatus());
            response.setTransactionDate(pedido.getCriadoEm());
            response.setOrderTotal(pedido.getValor());
            return response;
        }
        return null;
    }
    
    public List<TransactionResponseVO> mapToVOList(List<Pedido> pedidos){
        if(pedidos != null){
            List<TransactionResponseVO> responses = new ArrayList<TransactionResponseVO>();
            for(Pedido p: pedidos){
                responses.add(mapToVO(p));
            }
            return responses;
        }
        return null;
    }
}
