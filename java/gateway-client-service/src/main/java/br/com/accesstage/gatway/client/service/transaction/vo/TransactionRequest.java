package br.com.accesstage.gatway.client.service.transaction.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionRequest implements Serializable{

	private static final long serialVersionUID = -8157713910497055100L;
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
        return "TransactionRequest{" + "orderNumber=" + orderNumber + ", orderTotal=" + orderTotal + '}';
    }
}
