package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="utente")
public class Utente extends PanacheEntityBase {
   
    @Id
    @Column(name="id_utente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="cognome")
    private String cognome;

    @Column(name="eta")
    private Integer eta;

    @OneToOne
    @JoinColumn(name = "id_conto")
    private Conto conto;

    public Utente () {}

    public Utente(String nome, String cognome, Integer eta, Conto conto) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.conto = conto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public Conto getConto() {
        return conto;
    }

    public void setConto(Conto conto) {
        this.conto = conto;
    }

    @Override
    public String toString() {
        return "Utente [nome =" + nome + ", cognome =" + cognome + ", eta =" + eta + ", conto =" + conto + "]";
    }

    

}