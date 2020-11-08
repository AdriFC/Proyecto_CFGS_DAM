package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        //Unir los atributos con sus elementos del activity
        spinnerProvincia1 = (Spinner) findViewById(R.id.spinnerProvincia1);
        spinnerLocalidad1 = (Spinner) findViewById(R.id.spinnerLocalidad1);
        spinnerProvincia2 = (Spinner) findViewById(R.id.spinnerProvincia2);
        spinnerLocalidad2 = (Spinner) findViewById(R.id.spinnerLocalidad2);

        String [] opciones = {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez"};

        //Leer el archivo .csv con nombre de provincias para mostrar en los spinners de selección de provincia
        InputStream inputStream = getResources().openRawResource(R.raw.nombreprovicias);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> csvList = csvFile.read();



        //Accedo a la primera (única fila) del archivo .csv
        String[] provincias = csvList.get(0);

        //Se crea lista
        List<String> provinciasList = new ArrayList<String>();

        //Se le agrega como primer elemento el tezto informativo
        provinciasList.add("Selecciona una provincia");

        //Se convierte el array de strings a una lista
        for (int i = 0; i < provincias.length; i++)
        {
            provinciasList.add(provincias[i]);
        }

        //Añado el contenido correspondiente a cada spinner
        ArrayAdapter<String> provincia1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, provinciasList);
        spinnerProvincia1.setAdapter(provincia1);

        ArrayAdapter<String> localidad1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        spinnerLocalidad1.setAdapter(localidad1);
        spinnerLocalidad1.setEnabled(false);
        spinnerLocalidad1.setClickable(false);

        ArrayAdapter<String> provincia2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, provinciasList);
        spinnerProvincia2.setAdapter(provincia2);

        ArrayAdapter<String> localidad2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        spinnerLocalidad2.setAdapter(localidad2);
        spinnerLocalidad2.setEnabled(false);
        spinnerLocalidad2.setClickable(false);

        //Definir comportamiento al seleccionar opción en spinner de provincias
        spinnerProvincia1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                System.out.println(parent.getItemAtPosition(pos));
                String municipiosDeProvincia = "municipios_" + parent.getItemAtPosition(pos);
                municipiosDeProvincia = municipiosDeProvincia.toLowerCase();
                municipiosDeProvincia = municipiosDeProvincia.replace("ñ","n");
                municipiosDeProvincia = municipiosDeProvincia.replace('á','a');
                municipiosDeProvincia = municipiosDeProvincia.replace('é','e');
                municipiosDeProvincia = municipiosDeProvincia.replace('í','i');
                municipiosDeProvincia = municipiosDeProvincia.replace('ó','o');
                municipiosDeProvincia = municipiosDeProvincia.replace('ú','u');
                municipiosDeProvincia = municipiosDeProvincia.replace(" ","_");

                //System.out.println(municipiosDeProvincia);

                spinnerLocalidad1.setEnabled(true);
                spinnerLocalidad1.setClickable(true);

                InputStream isMunicipios1 = getResources().openRawResource(
                        getResources().getIdentifier(municipiosDeProvincia, "raw", getPackageName()));



                //Leer el archivo .csv con nombre de los municipios de la provincia seleccionada en los spinners de municipio
                CSVFile csvFileMunicipios1 = new CSVFile(isMunicipios1);
                List<String[]> csvListMunicipios1 = csvFileMunicipios1.read();

                //Accedo a la primera (única fila) del archivo .csv
                String[] municipios = csvListMunicipios1.get(0);

                //Se crea lista
                List<String> municipiosList = new ArrayList<String>();

                //Se le agrega como primer elemento el tezto informativo
                municipiosList.add("Selecciona un municipio");

                //Se convierte el array de strings a una lista
                for (int i = 0; i < municipios.length; i++)
                {
                    municipiosList.add(municipios[i]);
                }



            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }
}


