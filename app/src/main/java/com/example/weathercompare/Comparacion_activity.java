package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Interface.JsonPlaceHolderApi;
import Modelo.Model200;
import Modelo.Prediccion;
import Modelo.PrediccionMunicipio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comparacion_activity extends AppCompatActivity {

    //Atributos
    Spinner spinnerProvincia1;
    Spinner spinnerLocalidad1;
    Spinner spinnerProvincia2;
    Spinner spinnerLocalidad2;
    Button buttonCompara;

    String baseUrl = "https://opendata.aemet.es/";
    PrediccionMunicipio prediccion1;
    PrediccionMunicipio prediccion2;
    boolean pred1rx;
    boolean pred2rx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparacion_activity);

        //Unir los atributos con sus elementos del activity
        spinnerProvincia1 = (Spinner) findViewById(R.id.spinnerProvincia1);
        spinnerLocalidad1 = (Spinner) findViewById(R.id.spinnerLocalidad1);
        spinnerProvincia2 = (Spinner) findViewById(R.id.spinnerProvincia2);
        spinnerLocalidad2 = (Spinner) findViewById(R.id.spinnerLocalidad2);

        String [] opciones = {"Selecciona un municipio"};

        //Leer el archivo .csv con nombre de provincias para mostrar en los spinners de selección de provincia
        InputStream inputStream = getResources().openRawResource(R.raw.nombreprovicias);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> csvList = csvFile.read();

        //Accedo a la primera (única fila) del archivo .csv
        String[] provincias = csvList.get(0);

        //Se crea lista
        List<String> provinciasList = new ArrayList<String>();

        //Se le agrega como primer elemento el texto informativo
        provinciasList.add("Selecciona una provincia");

        //Se convierte el array de strings a una lista
        for (int i = 0; i < provincias.length; i++)
        {
            provinciasList.add(provincias[i]);
        }

        //Añado el contenido correspondiente a cada spinner
        ArrayAdapter<String> provincia1 = new ArrayAdapter<String>(this, R.layout.spinner_item_disenio, provinciasList);
        spinnerProvincia1.setAdapter(provincia1);

        ArrayAdapter<String> localidad1 = new ArrayAdapter<String>(this, R.layout.spinner_item_disenio, opciones);
        spinnerLocalidad1.setAdapter(localidad1);
        spinnerLocalidad1.setEnabled(false);
        spinnerLocalidad1.setClickable(false);

        ArrayAdapter<String> provincia2 = new ArrayAdapter<String>(this, R.layout.spinner_item_disenio, provinciasList);
        spinnerProvincia2.setAdapter(provincia2);

        ArrayAdapter<String> localidad2 = new ArrayAdapter<String>(this, R.layout.spinner_item_disenio, opciones);
        spinnerLocalidad2.setAdapter(localidad2);
        spinnerLocalidad2.setEnabled(false);
        spinnerLocalidad2.setClickable(false);

        //Definir comportamiento al seleccionar opción en spinner de provincias
        spinnerProvincia1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                //System.out.println(parent.getItemAtPosition(pos));
                if (pos != 0) {
                    //Creamos un string con el nombre de los .csv de los municipios a cargar
                    String municipiosDeProvincia = "municipios_" + parent.getItemAtPosition(pos);
                    //Se necesita convertir el nombre a minúsculas y eliminar ñ y ´
                    municipiosDeProvincia = municipiosDeProvincia.toLowerCase();
                    municipiosDeProvincia = municipiosDeProvincia.replace("ñ", "n");
                    municipiosDeProvincia = municipiosDeProvincia.replace('á', 'a');
                    municipiosDeProvincia = municipiosDeProvincia.replace('é', 'e');
                    municipiosDeProvincia = municipiosDeProvincia.replace('í', 'i');
                    municipiosDeProvincia = municipiosDeProvincia.replace('ó', 'o');
                    municipiosDeProvincia = municipiosDeProvincia.replace('ú', 'u');
                    municipiosDeProvincia = municipiosDeProvincia.replace(" ", "_");

                    //System.out.println(municipiosDeProvincia);
                    //System.out.println(getResources().getIdentifier(municipiosDeProvincia, "raw", getPackageName()));

                    //Se abre el .csv de municipios utilizando el nombre, no el id
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
                    for (int i = 0; i < municipios.length; i++) {
                        municipiosList.add(municipios[i]);
                    }

                    ArrayAdapter<String> localidad1 = new ArrayAdapter<String>(Comparacion_activity.this, R.layout.spinner_item_disenio, municipiosList);
                    spinnerLocalidad1.setAdapter(localidad1);
                    spinnerLocalidad1.setEnabled(true);
                    spinnerLocalidad1.setClickable(true);
                } else {
                    List<String> defaultText = new ArrayList<String>();
                    defaultText.add("Selecciona un municipio");
                    ArrayAdapter<String> localidad1 = new ArrayAdapter<String>(Comparacion_activity.this, R.layout.spinner_item_disenio, defaultText);
                    spinnerLocalidad1.setAdapter(localidad1);
                    spinnerLocalidad1.setEnabled(false);
                    spinnerLocalidad1.setClickable(false);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        //Definir comportamiento al seleccionar opción en spinner de provincias
        spinnerProvincia2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                //System.out.println(parent.getItemAtPosition(pos));
                if (pos != 0) {
                    String municipiosDeProvincia = "municipios_" + parent.getItemAtPosition(pos);
                    municipiosDeProvincia = municipiosDeProvincia.toLowerCase();
                    municipiosDeProvincia = municipiosDeProvincia.replace("ñ", "n");
                    municipiosDeProvincia = municipiosDeProvincia.replace('á', 'a');
                    municipiosDeProvincia = municipiosDeProvincia.replace('é', 'e');
                    municipiosDeProvincia = municipiosDeProvincia.replace('í', 'i');
                    municipiosDeProvincia = municipiosDeProvincia.replace('ó', 'o');
                    municipiosDeProvincia = municipiosDeProvincia.replace('ú', 'u');
                    municipiosDeProvincia = municipiosDeProvincia.replace(" ", "_");

                    System.out.println(municipiosDeProvincia);
                    System.out.println(getResources().getIdentifier(municipiosDeProvincia, "raw", getPackageName()));

                    InputStream isMunicipios2 = getResources().openRawResource(
                            getResources().getIdentifier(municipiosDeProvincia, "raw", getPackageName()));


                    //Leer el archivo .csv con nombre de los municipios de la provincia seleccionada en los spinners de municipio
                    CSVFile csvFileMunicipios2 = new CSVFile(isMunicipios2);
                    List<String[]> csvListMunicipios2 = csvFileMunicipios2.read();

                    //Accedo a la primera (única fila) del archivo .csv
                    String[] municipios = csvListMunicipios2.get(0);

                    //Se crea lista
                    List<String> municipiosList = new ArrayList<String>();

                    //Se le agrega como primer elemento el tezto informativo
                    municipiosList.add("Selecciona un municipio");

                    //Se convierte el array de strings a una lista
                    for (int i = 0; i < municipios.length; i++) {
                        municipiosList.add(municipios[i]);
                    }

                    ArrayAdapter<String> localidad2 = new ArrayAdapter<String>(Comparacion_activity.this, R.layout.spinner_item_disenio, municipiosList);
                    spinnerLocalidad2.setAdapter(localidad2);
                    spinnerLocalidad2.setEnabled(true);
                    spinnerLocalidad2.setClickable(true);
                } else {
                    List<String> defaultText = new ArrayList<String>();
                    defaultText.add("Selecciona un municipio");
                    ArrayAdapter<String> localidad1 = new ArrayAdapter<String>(Comparacion_activity.this, R.layout.spinner_item_disenio, defaultText);
                    spinnerLocalidad2.setAdapter(localidad1);
                    spinnerLocalidad2.setEnabled(false);
                    spinnerLocalidad2.setClickable(false);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        buttonCompara = findViewById(R.id.button);                     //Asocio el objeto botón de comparar con el botón del layout
        buttonCompara.setOnClickListener(new View.OnClickListener() {  //Asocio la función hacerComparacion al listener del botón
            @Override
            public void onClick(View v) {
                String provincia1 = "";
                String provincia2 = "";
                String localidad1 = "";
                String localidad2 = "";

                if(spinnerProvincia1.getSelectedItemPosition() != 0) { // Alguna provincia seleccionada
                    provincia1 = spinnerProvincia1.getSelectedItem().toString();
                    if(spinnerLocalidad1.getSelectedItemPosition() != 0) { //Alguna localidad seleccionada
                        localidad1 = spinnerLocalidad1.getSelectedItem().toString();
                    } else {
                        Toast.makeText(Comparacion_activity.this,getString(R.string.TextSelectMun),Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(Comparacion_activity.this, getString(R.string.TextSelectProv),Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinnerProvincia2.getSelectedItemPosition() != 0) { // Alguna provincia seleccionada
                    provincia2 = spinnerProvincia2.getSelectedItem().toString();
                    if(spinnerLocalidad2.getSelectedItemPosition() != 0) { //Alguna localidad seleccionada
                        localidad2 = spinnerLocalidad2.getSelectedItem().toString();
                    } else {
                        Toast.makeText(Comparacion_activity.this,getString(R.string.TextSelectMun),Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(Comparacion_activity.this,getString(R.string.TextSelectProv),Toast.LENGTH_SHORT).show();
                    return;
                }

                System.out.println("Localidades y provincias validas");
                int cod1 = obtenerCodigoLocalidad(provincia1, localidad1);
                int cod2 = obtenerCodigoLocalidad(provincia2, localidad2);

                if(cod1 != -1 && cod2 != -1) {
                    hacerComparacion (cod1,cod2);
                } else{
                    Toast.makeText(Comparacion_activity.this,getString(R.string.TextErrorCodProv),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int obtenerCodigoLocalidad(String provincia, String localidad){
        int codigo = -1;
        InputStream is = getResources().openRawResource(R.raw.codmunpro);
        CSVFile csvFile = new CSVFile(is);
        List<String[]> csvList = csvFile.read();

        System.out.println(csvList.size());
        for(String[] stringArray : csvList) {
            //stringArray[1] y StringArray[2] forman el id
            //stringArray[4] tiene el nombre de la localidad
            //stringArray[5] tiene el nombre de la provincia
            //System.out.println("¿" + stringArray[4] + " = " + localidad + "?" + (stringArray[4].equals(localidad)));
            //System.out.println("¿" + stringArray[5] + " = " + provincia + "?" + (stringArray[5].equals(provincia)));
            if(stringArray[4].equals(localidad) && stringArray[5].equals(provincia)) {
                codigo = Integer.parseInt(stringArray[1]) * 1000 + Integer.parseInt(stringArray[2]);
                //System.out.println("Codigo de " + localidad + ": " + codigo);
                break;
            }
        }
        return codigo;
    }

    private void hacerComparacion(int cod1, int cod2){
        getModel200(cod1, 1);
        getModel200(cod2, 2);
    }

    private void getModel200(int cod, final int index)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Se llama a la interfaz
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        String url = "opendata/api/prediccion/especifica/municipio/diaria/" +
                String.format("%05d", cod) +
                "/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZHJpODZ2aWdvQGdtYWlsLmNvbSIsImp0aSI6IjJkNzgzYjhhLTFjMTUtNGJjNS04ZmJkLTMwZmY4NWM2NWUyNSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjAzMDE5NTg0LCJ1c2VySWQiOiIyZDc4M2I4YS0xYzE1LTRiYzUtOGZiZC0zMGZmODVjNjVlMjUiLCJyb2xlIjoiIn0.a1IIOmDUM1FI6neNmgLeT728iLAKa26mxia-Oe5sOWs";

        Call<Model200> call = jsonPlaceHolderApi.getModel200(url);
        call.enqueue(new Callback<Model200>() {
            @Override
            public void onResponse(Call<Model200> call, Response<Model200> response) {
                if(!response.isSuccessful()){
                    System.out.println("Operacion 1 Incorrecta");
                    return;
                }
                getPrediction(response.body(), index);
            }

            @Override
            public void onFailure(Call<Model200> call, Throwable t) {

            }
        });
    }

    //Método para traer los datos
    private void getPrediction(Model200 model200, final int index){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        String url2 = model200.getDatos();
        url2 = url2.replace(baseUrl,"");

        System.out.println("Lanzando prediccion" + index);
        Call<List<PrediccionMunicipio>> callPrediccion = jsonPlaceHolderApi.getPrediccion(url2);

        callPrediccion.enqueue(new Callback<List<PrediccionMunicipio>>() {
            @Override
            public void onResponse(Call<List<PrediccionMunicipio>> call, Response<List<PrediccionMunicipio>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Operacion Incorrecta");
                    return;
                }

                List<PrediccionMunicipio> prediccionMunicipioList = response.body();
                //Esta peticion es en realidad una lista de un único elemento
                PrediccionMunicipio prediccion = prediccionMunicipioList.get(0);

                if(index == 1) {
                    System.out.println("Recibido prediccion 1");
                    comprobarRecepcion(prediccion, index);

                } else {
                    System.out.println("Recibido prediccion 2");
                    comprobarRecepcion(prediccion, index);
                }
            }

            @Override
            public void onFailure(Call<List<PrediccionMunicipio>> call, Throwable t) {

            }
        });
    }

    //Esta funcion me permite sincronizar las respuestas de las peticiones asincronas
    //Comprueba cuando me llega una de las respuestas, si la otra tambien ha llegado
    //En ese caso, ya puedo procesar la comparacion
    private void comprobarRecepcion(PrediccionMunicipio prediccionMunicipio, int index){
        if(index == 1) {
            pred1rx = true;
            prediccion1 = prediccionMunicipio;
            if(pred2rx == true) { // Ambas recepciones terminadas
                procesarComparacion();
            }

        } else {
            pred2rx = true;
            prediccion2 = prediccionMunicipio;
            if (pred1rx == true) { // Ambas recepciones terminadas
                procesarComparacion();
            }
        }
    }

    private void procesarComparacion(){
        //Comparacion comparacion = new Comparacion();

        Intent intent = new Intent(this, ResultadoComparacionActivity.class);
        System.out.println("Provincia; " + prediccion1.getProvincia() + "Localidad: " + prediccion1.getNombre());
        intent.putExtra("prediccionMunicipio1", prediccion1);
        intent.putExtra("prediccionMunicipio2", prediccion2);
        startActivity(intent);
    }
}


