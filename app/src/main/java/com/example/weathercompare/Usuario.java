package com.example.weathercompare;

public class Usuario {

    //Atributos
    private int id;
    private String nombre;
    private String email;
    private String contraseña;
    private Historial historial;

    //Constructores
    public Usuario()
    {
        this.id = 0;
        this.nombre = "";
        this.email = "";
        this.contraseña = "";
        this.historial = new Historial();
    }
    public Usuario(int id, String nombre, String email, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.historial = new Historial();
    }
    public Usuario(int id, String nombre, String email, String contraseña, Historial historial) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.historial = historial;
    }

    //Getters, setters & toString
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

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", historial='" + historial.toString() + '\'' +
                '}';
    }
}
