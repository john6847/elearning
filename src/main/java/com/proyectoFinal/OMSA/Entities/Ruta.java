package com.proyectoFinal.OMSA.Entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name ="ruta")
public class Ruta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DecimalMin("0.00")
    private Float distanciaTotal;
    @Min(1483228800)
    private Long fechaCreada;
    @Min(1483228800)
    private Long fechaUltimaModificacion;
    @Size(min=2, max = 100)
    private String ciudad;
    @NotNull
    @Size(min=2, max = 100)
        private String nombreCorredor;
    @NotNull
    private Boolean esDireccionSubida;
    private Boolean habilitado=true;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List <Coordenada> coordenadas;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Parada> paradas = new ArrayList<>();

    public Ruta(){

    }

    public Ruta(Float distanciaTotal, Long fechaCreada, Long fechaUltimaModificacion,
                String ciudad, String nombreCorredor, Boolean esDireccionSubida,
                Boolean habilitado, List<Coordenada> coordenadas, List<Parada> paradas) {
        this.distanciaTotal = distanciaTotal;
        this.fechaCreada = fechaCreada;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.ciudad = ciudad;
        this.nombreCorredor = nombreCorredor;
        this.esDireccionSubida = esDireccionSubida;
        this.habilitado = habilitado;
        this.coordenadas = coordenadas;
        this.paradas = paradas;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(Float distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Long getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(Long fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public Long getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Long fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreCorredor() {
        return nombreCorredor;
    }

    public void setNombreCorredor(String nombreCorredor) {
        this.nombreCorredor = nombreCorredor;
    }

    public Boolean getEsDireccionSubida() {
        return esDireccionSubida;
    }

    public void setEsDireccionSubida(Boolean esDireccionSubida) {
        this.esDireccionSubida = esDireccionSubida;
    }

    public List<Coordenada> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }
}
