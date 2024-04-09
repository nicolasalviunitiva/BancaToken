package org.acme.model;

import java.math.BigDecimal;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "conto_corrente")
public class Conto extends PanacheEntityBase{

    @Id
    @Column(name="id_conto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="numero_conto")
    private String numeroConto;

    
    @Column(name="saldo")
    private BigDecimal saldo;
    
    @Column(name="attivo")
    private boolean attivo;
    
    public Conto () {}
    
    public Conto(String numeroConto, BigDecimal saldo, boolean attivo) {
        this.numeroConto = numeroConto;
        this.saldo = saldo;
        this.attivo = attivo;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(String numeroConto) {
        this.numeroConto = numeroConto;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
    
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
        public boolean isAttivo() {
            return attivo;
        }
    
        public void setAttivo(boolean attivo) {
            this.attivo = attivo;
        }

        @Override
        public String toString() {
            return "Conto [id=" + id + ", numeroConto=" + numeroConto + ", saldo=" + saldo + ", attivo=" + attivo + "]";
        }
    

    
    
    
}
