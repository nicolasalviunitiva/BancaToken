package org.acme.model;

import java.math.BigDecimal;

import org.acme.enumer.Stati;

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
    
    @Column(name="id_user")
    private Long idUser;

    @Column(name="importo")
    private BigDecimal importo;

    @Column(name="stato")
    private String stato;

    public RichiestaMutuo(){
        this.stato = Stati.LAVORAZIONE.getValue();
    }

    public RichiestaMutuo(Long idUser, BigDecimal importo, String stato) {
        this.idUser = idUser;
        this.importo = importo;        
        this.stato = stato;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "RichiestaMutuo [id=" + id + ", idUser=" + idUser + ", importo=" + importo + ", stato=" + stato
                + "]";
    }


}
