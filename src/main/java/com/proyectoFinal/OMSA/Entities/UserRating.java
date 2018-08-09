package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dany on 06/10/2017.
 */
@Entity
@Table(name = "userRating")
public class UserRating implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fechaPublicada;
    private int numeroDePuntuacion;
    //@Lob
    private String comentario;

    public UserRating() {

    }

    public UserRating(Long fechaPublicada, int numeroDePuntuacion, String comentario) {
        this.fechaPublicada = fechaPublicada;
        this.numeroDePuntuacion = numeroDePuntuacion;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFechaPublicada() {
        return fechaPublicada;
    }

    public void setFechaPublicada(Long fechaPublicada) {
        this.fechaPublicada = fechaPublicada;
    }

    public int getNumeroDePuntuacion() {
        return numeroDePuntuacion;
    }

    public void setNumeroDePuntuacion(int numeroDePuntuacion) {
        this.numeroDePuntuacion = numeroDePuntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
