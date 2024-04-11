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
@Table(name = "richiesta_mutuo")
public class RichiestaMutuo extends PanacheEntityBase {

    @Id
    @Column(name="id_richiesta")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="id_utente")
    private Long idUtente;

    @Column(name="importo")
    private BigDecimal importo;

    @Column(name="lavorata")
    private boolean lavorata;

    @Column(name="accettata")
    private boolean accettata;

    public RichiestaMutuo(){}

    public RichiestaMutuo(Long idUtente, BigDecimal importo) {
        this.idUtente = idUtente;
        this.importo = importo;
        this.lavorata = false;
        this.accettata = false;
    }

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public boolean isLavorata() {
        return lavorata;
    }

    public void setLavorata(boolean lavorata) {
        this.lavorata = lavorata;
    }

    public boolean isAccettata() {
        return accettata;
    }

    public void setAccettata(boolean accettata) {
        this.accettata = accettata;
    }

    @Override
    public String toString() {
        return "RichiestaMutuo [id=" + id + ", idUtente=" + idUtente + ", importo=" + importo + ", lavorata=" + lavorata
                + ", accettata=" + accettata + "]";
    }

 

}
