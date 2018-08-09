package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name = "coordenada")
public class Coordenada implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private double latitude;
    @NotNull
    private double longitud;

    private Boolean habilitado = true;

    public Coordenada(){

    }

    public Coordenada(double latitude, double longitud) {
        this.latitude = latitude;
        this.longitud = longitud;

    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
