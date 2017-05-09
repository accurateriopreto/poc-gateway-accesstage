package br.com.accesstage.gateway.consumer.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ConsumerTransactionRequest  implements Serializable{

	private static final long serialVersionUID = 9198483373427599082L;
	
	private String orderNumber;
    
	private BigDecimal orderTotal;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "ConsumerTransactionRequest{" + "orderNumber=" + orderNumber + ", orderTotal=" + orderTotal + '}';
    }
}
