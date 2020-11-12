
package Modelo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SensTermica {

    @SerializedName("maxima")
    @Expose
    private Integer maxima;
    @SerializedName("minima")
    @Expose
    private Integer minima;
    @SerializedName("dato")
    @Expose
    private List<Dato> dato = null;

    public Integer getMaxima() {
        return maxima;
    }

    public void setMaxima(Integer maxima) {
        this.maxima = maxima;
    }

    public Integer getMinima() {
        return minima;
    }

    public void setMinima(Integer minima) {
        this.minima = minima;
    }

    public List<Dato> getDato() {
        return dato;
    }

    public void setDato(List<Dato> dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return "SensTermica{" +
                "maxima=" + maxima +
                ", minima=" + minima +
                ", dato=" + dato +
                '}';
    }
}
