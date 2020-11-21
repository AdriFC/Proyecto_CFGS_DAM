package com.example.weathercompare;

import java.util.Date;

public class Busqueda {
    //Atributos
    private int id;
    private String ubicacion;
    private String provincia;
    private Date fecha;
    private float tempMin;
    private float tempMax;
    private float tempMedia;
    private float vientoMedia;
    private float vientoRacha;
    private float precipitaciones;
    private String estadoCielo;


    //Constructor
    public Busqueda(int id, String ubicacion, String provincia, Date fecha, float tempMin, float tempMax, float tempMedia, float vientoMedia, float vientoRacha, float precipitaciones, String estadoCielo) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.provincia = provincia;
        this.fecha = fecha;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.tempMedia = tempMedia;
        this.vientoMedia = vientoMedia;
        this.vientoRacha = vientoRacha;
        this.precipitaciones = precipitaciones;
        this.estadoCielo = estadoCielo;
    }

    //Constructor vacío
    public Busqueda() {
        this.id = 0;
        this.ubicacion = "";
        this.provincia = "";
        this.fecha = new Date();
        this.tempMin = 0;
        this.tempMax = 0;
        this.tempMedia = 0;
        this.vientoMedia = 0;
        this.vientoRacha = 0;
        this.precipitaciones = 0;
        this.estadoCielo = "";
    }

    //Getters&Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getProvincia() { return provincia; }

    public void setProvincia(String provincia) { this.provincia = provincia; }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public float getTempMedia() {
        return tempMedia;
    }

    public void setTempMedia(float tempMedia) {
        this.tempMedia = tempMedia;
    }

    public float getVientoMedia() {
        return vientoMedia;
    }

    public void setVientoMedia(float vientoMedia) {
        this.vientoMedia = vientoMedia;
    }

    public float getVientoRacha() {
        return vientoRacha;
    }

    public void setVientoRacha(float vientoRacha) {
        this.vientoRacha = vientoRacha;
    }

    public float getPrecipitaciones() {
        return precipitaciones;
    }

    public void setPrecipitaciones(float precipitaciones) {
        this.precipitaciones = precipitaciones;
    }

    public String getEstadoCielo() {
        return estadoCielo;
    }

    public void setEstadoCielo(String estadoCielo) {
        this.estadoCielo = estadoCielo;
    }

    //ToString
    @Override
    public String toString() {
        return "Busqueda{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", provincia='" + provincia + '\'' +
                ", fecha=" + fecha +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", tempMedia=" + tempMedia +
                ", vientoMedia=" + vientoMedia +
                ", vientoRacha=" + vientoRacha +
                ", precipitaciones=" + precipitaciones +
                ", estadoCielo=" + estadoCielo +
                '}';
    }
}
