package br.com.accesstage.gatway.client.service.transaction.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionResponse implements Serializable {
	
	private static final long serialVersionUID = -8714243620681239056L;
    
    private String orderNumber;
    private BigDecimal orderTotal;
    private String orderStatus;
    private Date transactionDate;
    private String authorizationCode;

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}
