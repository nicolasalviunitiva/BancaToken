package org.acme.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mutui")
public class Mutui extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_mutuo")
    private Long id;

    @Column(name="importo")
    private BigDecimal importo;

    @Column(name="nRate")
    private int nRate;

    @Column(name="impRata")
    private BigDecimal impRata;

    @Column(name="stato")
    private String stato;

    @Column(name="dataInizio")
    private LocalDate dataInizio;

    @Column(name="idUser")
    private Long idUser;

    @Column(name="ratePagate")
    private int ratePagate;

    
    public int getRatePagate() {
        return ratePagate;
    }

    public void setRatePagate(int ratePagate) {
        this.ratePagate = ratePagate;
    }

    public Mutui(){}

    public Mutui(BigDecimal importo, int nRate, BigDecimal impRata, String stato, LocalDate dataInizio, Long idUser) {
        this.importo = importo;
        this.nRate = nRate;
        this.impRata = impRata;
        this.stato = stato;
        this.dataInizio = dataInizio;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public int getnRate() {
        return nRate;
    }

    public void setnRate(int nRate) {
        this.nRate = nRate;
    }

    public BigDecimal getImpRata() {
        return impRata;
    }

    public void setImpRata(BigDecimal impRata) {
        this.impRata = impRata;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Mutui [id=" + id + ", importo=" + importo + ", nRate=" + nRate + ", impRata=" + impRata + ", stato="
                + stato + ", dataInizio=" + dataInizio + ", idUser=" + idUser + "]";
    }

    

}
