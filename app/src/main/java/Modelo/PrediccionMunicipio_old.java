package Modelo;

import java.io.Serializable;

public class PrediccionMunicipio_old implements Serializable {

    //private Temperatura temperatura;
    private String nombre;
    private String provincia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "PrediccionMunicipio{" +
                "nombre='" + nombre + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
