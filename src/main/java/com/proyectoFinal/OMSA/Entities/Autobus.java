package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.DataInput;
import java.io.Serializable;
import java.sql.Date;

//import javax.persistence.*;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name = "autobus")
public class Autobus implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 35)
    private String modelo;
    @Max(100)
    @Min(0)
    private Integer cantidadDeAsientos;
    @DecimalMax("35000.00")
    @DecimalMin("0.00")
    private Float peso;
    @ManyToOne
    private Ruta ruta; //Updatable
    @ManyToOne
    private Parada ultimaParada; // Updatable
    @Min(1950)
    private Integer anoFabricacion;
    private Boolean activo;  //Updatable
    @Size(min=2, max = 100)
    @Pattern(regexp = "^(\\s)*[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*(\\s)*$")
    private String conductor;
    @Size(min=5, max = 6)
    @Pattern(regexp = "([0-9]{2})((-[0-9]{3})|([0-9]{3}))")
    private String matricula;
    @Min(1483228800)
    private Long fechaCreada; //fecha agregada en la base de datos
    @Min(1483228800)
    private Long ultimaFechaModificada; // Updatable
    @NotNull
    @Min(10)
    @Max(100)
    private Integer precio;
    @NotNull
    private Boolean tieneAireAcondicionado;
    @Max(100)
    @Min(0)
    private Integer cantidadDePasajerosActual; //Updatable
    @OneToOne(cascade = CascadeType.ALL)
    private Coordenada coordenada; //Updatable
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9-]+$")
    @Size(max = 100)
    private String raspberryPiNumeroSerial;
    private Boolean habilitado=true;

    public Autobus() {

    }

    public Autobus(Boolean habilitado, String modelo, Integer cantidadDeAsientos,
                   Float peso, Ruta ruta, Parada ultimaParada,
                   Integer anoFabricacion, Boolean activo,
                   String conductor, String matricula,
                   Long fechaCreada, Long ultimaFechaModificada,
                   Integer precio, Boolean tieneAireAcondicionado,
                   Integer cantidadDePasajerosActual,
                   Coordenada coordenada, String raspberryPiNumeroSerial
                   ) {
        this.modelo = modelo;
        this.cantidadDeAsientos = cantidadDeAsientos;
        this.peso = peso;
        this.ruta = ruta;
        this.ultimaParada = ultimaParada;
        this.anoFabricacion = anoFabricacion;
        this.activo = activo;
        this.conductor = conductor;
        this.matricula = matricula;
        this.fechaCreada = fechaCreada;
        this.ultimaFechaModificada = ultimaFechaModificada;
        this.precio = precio;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.cantidadDePasajerosActual = cantidadDePasajerosActual;
        this.coordenada = coordenada;
        this.raspberryPiNumeroSerial = raspberryPiNumeroSerial;
        this.habilitado = habilitado;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getRaspberryPiNumeroSerial() {
        return raspberryPiNumeroSerial;
    }

    public void setRaspberryPiNumeroSerial(String raspberryPiNumeroSerial) {
        this.raspberryPiNumeroSerial = raspberryPiNumeroSerial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCantidadDeAsientos() {
        return cantidadDeAsientos;
    }

    public void setCantidadDeAsientos(Integer cantidadDeAsientos) {
        this.cantidadDeAsientos = cantidadDeAsientos;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Parada getUltimaParada() {
        return ultimaParada;
    }

    public void setUltimaParada(Parada ultimaParada) {
        this.ultimaParada = ultimaParada;
    }

    public Integer getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(Integer anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public Long getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(Long fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public Long getUltimaFechaModificada() {
        return ultimaFechaModificada;
    }

    public void setUltimaFechaModificada(Long ultimaFechaModificada) {
        this.ultimaFechaModificada = ultimaFechaModificada;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Boolean getTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(Boolean tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public Integer getCantidadDePasajerosActual() {
        return cantidadDePasajerosActual;
    }

    public void setCantidadDePasajerosActual(Integer cantidadDePasajerosActual) {
        this.cantidadDePasajerosActual = cantidadDePasajerosActual;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }



}
