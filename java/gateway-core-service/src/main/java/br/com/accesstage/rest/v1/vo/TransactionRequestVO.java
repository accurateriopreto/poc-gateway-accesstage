package br.com.accesstage.rest.v1.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caio
 */
@XmlRootElement
public class TransactionRequestVO implements Serializable{

    private static final long serialVersionUID = 9213867605889489410L;
    
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
        return "TransactionRequestVO{" + "orderNumber=" + orderNumber + ", orderTotal=" + orderTotal + '}';
    }


    
}
