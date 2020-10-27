package com.example.weathercompare;

public class Usuario {

    //Atributos
    private int id;
    private String nombre;
    private String email;
    private String contraseña;
    private String historial;

    //Constructor con parámetros
    public Usuario(int id, String nombre, String email, String contraseña, String historial) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.historial = historial;
    }

    //Getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }


}
