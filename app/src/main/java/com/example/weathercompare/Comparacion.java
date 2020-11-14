package com.example.weathercompare;

import java.io.Serializable;
import java.util.Date;

public class Comparacion implements Serializable {
    //Atributos
    private Date fecha;
    private Busqueda busqueda1;
    private Busqueda busqueda2;

    //Constructor
    public Comparacion(Date fecha, Busqueda busqueda1, Busqueda busqueda2) {
        this.fecha = fecha;
        this.busqueda1 = busqueda1;
        this.busqueda2 = busqueda2;
    }

    //Constructor vacío
    public Comparacion() {
        this.fecha = new Date();
        this.busqueda1 = new Busqueda();
        this.busqueda2 = new Busqueda();
    }

    //Getters & Setters
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Busqueda getBusqueda1() {
        return busqueda1;
    }

    public void setBusqueda1(Busqueda busqueda1) {
        this.busqueda1 = busqueda1;
    }

    public Busqueda getBusqueda2() {
        return busqueda2;
    }

    public void setBusqueda2(Busqueda busqueda2) {
        this.busqueda2 = busqueda2;
    }

    //ToString
    @Override
    public String toString() {
        return "Comparacion{" +
                "fecha=" + fecha +
                ", busqueda1=" + busqueda1 +
                ", busqueda2=" + busqueda2 +
                '}';
    }
}
