package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    //Atributos
    TextView textoHistorial;
    TextView historial1;
    TextView historial2;
    TextView historial3;
    TextView historial4;
    TextView historial5;
    TextView historial6;

    List<Comparacion> comparacionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        textoHistorial = findViewById(R.id.TextoHistorial);
        historial1 = findViewById(R.id.H1);
        historial2 = findViewById(R.id.H2);
        historial3 = findViewById(R.id.H3);
        historial4 = findViewById(R.id.H4);
        historial5 = findViewById(R.id.H5);
        historial6 = findViewById(R.id.H6);

        // Deshabilitamos por defecto que podamos clicar en los textview
        historial1.setEnabled(false);
        historial2.setEnabled(false);
        historial3.setEnabled(false);
        historial4.setEnabled(false);
        historial5.setEnabled(false);
        historial6.setEnabled(false);

        // Recuperamos los datos de las ultimas comparaciones
        BaseDeDatos bd = new BaseDeDatos(this, "android", null, 1);
        comparacionList = bd.getDataComparacion(UsuarioHolder.getInstance().getUsuarioLogueado().getId());

        // Guardamos los datos en el historial del usuario
        UsuarioHolder.getInstance().getUsuarioLogueado().setHistorial(new Historial(comparacionList));

        // dateFormat para dar formato a la fecha de la comparacion
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Mostramos todas las comparaciones del usuario logueado
        for (Comparacion comparacion : comparacionList)
        {
            System.out.println("Comparacion: " + comparacion.toString());
        }

        if (comparacionList.size() > 0) {
            historial1.setText(
                    dateFormat.format(comparacionList.get(0).getFecha()) +
                    " [" +
                    comparacionList.get(0).getBusqueda1().getUbicacion() +
                    " vs " +
                    comparacionList.get(0).getBusqueda2().getUbicacion() +
                    "]");
            historial1.setEnabled(true);

            if (comparacionList.size() > 1) {
                historial2.setText(
                        dateFormat.format(comparacionList.get(1).getFecha()) +
                        " [" +
                        comparacionList.get(1).getBusqueda1().getUbicacion() +
                        " vs " +
                        comparacionList.get(1).getBusqueda2().getUbicacion() +
                        "]");
                historial2.setEnabled(true);
            }
            if (comparacionList.size() > 2) {
                historial3.setText(
                        dateFormat.format(comparacionList.get(2).getFecha()) +
                        " [" +
                        comparacionList.get(2).getBusqueda1().getUbicacion() +
                        " vs " +
                        comparacionList.get(2).getBusqueda2().getUbicacion() +
                        "]");
                historial3.setEnabled(true);
            }
            if (comparacionList.size() > 3) {
                historial4.setText(
                        dateFormat.format(comparacionList.get(3).getFecha()) +
                        " [" +
                        comparacionList.get(3).getBusqueda1().getUbicacion() +
                        " vs " +
                        comparacionList.get(3).getBusqueda2().getUbicacion() +
                        "]");
                historial4.setEnabled(true);
            }
            if (comparacionList.size() > 4) {
                historial5.setText(
                        dateFormat.format(comparacionList.get(4).getFecha()) +
                        " [" +
                        comparacionList.get(4).getBusqueda1().getUbicacion() +
                        " vs " +
                        comparacionList.get(4).getBusqueda2().getUbicacion() +
                        "]");
                historial5.setEnabled(true);
            }
            if (comparacionList.size() > 5) {
                historial6.setText(
                        dateFormat.format(comparacionList.get(5).getFecha()) +
                        " [" +
                        comparacionList.get(5).getBusqueda1().getUbicacion() +
                        " vs " +
                        comparacionList.get(5).getBusqueda2().getUbicacion() +
                        "]");
                historial6.setEnabled(true);
            }
        } else {
            historial1.setText("Todavía no has hecho ninguna comparación");
        }

        historial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, ResultadoHistorial.class);
                intent.putExtra("comparacion", comparacionList.get(0));
                startActivity(intent);
            }
        });

        historial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, ResultadoHistorial.class);
                intent.putExtra("comparacion", comparacionList.get(1));
                startActivity(intent);

            }
        });

        historial3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, ResultadoHistorial.class);
                intent.putExtra("comparacion", comparacionList.get(2));
                startActivity(intent);
            }
        });

        historial4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, ResultadoHistorial.class);
                intent.putExtra("comparacion", comparacionList.get(3));
                startActivity(intent);
            }
        });

        historial5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, ResultadoHistorial.class);
                intent.putExtra("comparacion", comparacionList.get(4));
                startActivity(intent);
            }
        });

        historial6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, ResultadoHistorial.class);
                intent.putExtra("comparacion", comparacionList.get(5));
                startActivity(intent);
            }
        });

    }
}