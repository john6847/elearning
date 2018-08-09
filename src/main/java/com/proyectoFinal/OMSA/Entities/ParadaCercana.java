package com.proyectoFinal.OMSA.Entities;

/**
 * Created by Dany on 02/10/2017.
 */
public class ParadaCercana {
    private Parada paradaActual;
    private Double distancia;
    private Coordenada destOrig;
    private Parada paradaSalida;
    private Parada paradaLLegada;

    public ParadaCercana(Parada paradaActual, Double distancia, Coordenada destOrig, Parada paradaSalida, Parada paradaLLegada) {
        this.paradaActual = paradaActual;
        this.distancia = distancia;
        this.destOrig = destOrig;
        this.paradaSalida = paradaSalida;
        this.paradaLLegada = paradaLLegada;
    }

    public ParadaCercana() {
    }

    public Parada getParadaActual() {
        return paradaActual;
    }

    public void setParadaActual(Parada paradaActual) {
        this.paradaActual = paradaActual;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Coordenada getDestOrig() {
        return destOrig;
    }

    public void setDestOrig(Coordenada destOrig) {
        this.destOrig = destOrig;
    }

    public Parada getParadaSalida() {
        return paradaSalida;
    }

    public void setParadaSalida(Parada paradaSalida) {
        this.paradaSalida = paradaSalida;
    }

    public Parada getParadaLLegada() {
        return paradaLLegada;
    }

    public void setParadaLLegada(Parada paradaLLegada) {
        this.paradaLLegada = paradaLLegada;
    }
}
