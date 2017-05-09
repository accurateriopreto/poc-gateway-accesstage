package br.com.accesstage.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author caio
 */
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = -1645197023839942327L;
    @Id
    private String codigo;
    @Column
    private BigDecimal valor;
    @Column
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", insertable = true, updatable = false)
    private Date criadoEm;
    @Column
    private String autorizacao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    @PrePersist
    private void onCreate() {
        setCriadoEm(Calendar.getInstance().getTime());
    }
    
}
