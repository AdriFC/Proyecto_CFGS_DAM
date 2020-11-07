package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Comparacion_activity extends AppCompatActivity {

    //Atributos
    Spinner spinnerProvincia1;
    Spinner spinnerLocalidad1;
    Spinner spinnerProvincia2;
    Spinner spinnerLocalidad2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparacion_activity);

        spinnerProvincia1 = (Spinner) findViewById(R.id.spinnerProvincia1);
        spinnerLocalidad1 = (Spinner) findViewById(R.id.spinnerLocalidad1);
        spinnerProvincia2 = (Spinner) findViewById(R.id.spinnerProvincia2);
        spinnerLocalidad2 = (Spinner) findViewById(R.id.spinnerLocalidad2);

        String [] opciones = {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez"};

        ArrayAdapter<String> provincia1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        spinnerProvincia1.setAdapter(provincia1);

        ArrayAdapter<String> localidad1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        spinnerLocalidad1.setAdapter(localidad1);

        ArrayAdapter<String> provincia2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        spinnerProvincia2.setAdapter(provincia2);

        ArrayAdapter<String> localidad2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        spinnerLocalidad2.setAdapter(localidad2);
    }
}