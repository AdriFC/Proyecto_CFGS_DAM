package Modelo;

public class Temperatura {

    //Atributos
    private int maxima;
    private int minima;

    //Getters&Setters
    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    //ToString
    @Override
    public String toString() {
        return "PrediccionMunicipio{" +
                "maxima=" + maxima +
                ", minima=" + minima +
                '}';
    }
}
