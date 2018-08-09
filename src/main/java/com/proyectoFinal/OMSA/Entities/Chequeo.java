package com.proyectoFinal.OMSA.Entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name = "chequeo")
public class Chequeo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id; //to be thinking
    @NotNull
    @Min(1483228800)
    private Long fechaRegistrada;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="AUTOBUS_ID")
    private Autobus autobus;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)// to be thought
    private Parada parada;
    @NotNull
    private Boolean esEntrada;
    private Long idRuta;
    public Chequeo() {

    }

    public Chequeo(Long fechaRegistrada, Autobus autobus, Parada parada, Boolean esEntrada, Long idRuta) {
        this.fechaRegistrada = fechaRegistrada;
        this.autobus = autobus;
        this.parada = parada;
        this.esEntrada = esEntrada;
        this.idRuta = idRuta;
    }

    public Long getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Long idRuta) {
        this.idRuta = idRuta;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getFechaRegistrada() {
        return fechaRegistrada;
    }

    public void setFechaRegistrada(Long fechaRegistrada) {
        this.fechaRegistrada = fechaRegistrada;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    public Boolean getEsEntrada() {
        return esEntrada;
    }

    public void setEsEntrada(Boolean esEntrada) {
        this.esEntrada = esEntrada;
    }


}
