package com.example.weathercompare;

import java.util.ArrayList;
import java.util.List;

public class Historial {

    private List<Comparacion> historial;

    public Historial() {
        historial = new ArrayList<Comparacion>();
    }
    public Historial(List<Comparacion> historial) {
        this.historial = historial;
    }

    public List<Comparacion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Comparacion> historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Historial TBD";
    }
}
