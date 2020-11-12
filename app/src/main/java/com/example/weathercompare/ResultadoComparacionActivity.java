package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Interface.JsonPlaceHolderApi;
import Modelo.Model200;
import Modelo.PrediccionMunicipio;
import Modelo.PrediccionMunicipio_old;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultadoComparacionActivity extends AppCompatActivity {

    //Atributo del TextView para recibir la información
    private TextView myJsonTextView;
    //private Model200 model200;
    String baseUrl = "https://opendata.aemet.es/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadocomparacion);

        //Enlace del atributo con el textView del layout
        myJsonTextView = findViewById(R.id.JsonTextView);
        getModel200();
    }

    private void getModel200()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Se llama a la interfaz
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        String url = "opendata/api/prediccion/especifica/municipio/diaria/" + "36039" + "/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZHJpODZ2aWdvQGdtYWlsLmNvbSIsImp0aSI6IjJkNzgzYjhhLTFjMTUtNGJjNS04ZmJkLTMwZmY4NWM2NWUyNSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjAzMDE5NTg0LCJ1c2VySWQiOiIyZDc4M2I4YS0xYzE1LTRiYzUtOGZiZC0zMGZmODVjNjVlMjUiLCJyb2xlIjoiIn0.a1IIOmDUM1FI6neNmgLeT728iLAKa26mxia-Oe5sOWs";

        Call<Model200> call = jsonPlaceHolderApi.getModel200(url);
        call.enqueue(new Callback<Model200>() {
            @Override
            public void onResponse(Call<Model200> call, Response<Model200> response) {
                if(!response.isSuccessful()){

                    System.out.println("Operacion 1 Incorrecta");
                    myJsonTextView.setText("código: " + response.code());
                    return;
                }

                getPrediction(response.body());
            }

            @Override
            public void onFailure(Call<Model200> call, Throwable t) {

            }
        });
    }

    //Método para traer los datos
    private void getPrediction(Model200 model200){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        String url2 = model200.getDatos();
        //System.out.println(url2);
        url2 = url2.replace(baseUrl,"");
        //System.out.println(url2);

        Call<List<PrediccionMunicipio>> callPrediccion = jsonPlaceHolderApi.getPrediccion(url2);
        callPrediccion.enqueue(new Callback<List<PrediccionMunicipio>>() {
            @Override
            public void onResponse(Call<List<PrediccionMunicipio>> call, Response<List<PrediccionMunicipio>> response) {
                if(!response.isSuccessful()){

                    System.out.println("Operacion Incorrecta");
                    myJsonTextView.setText("código: " + response.code());
                    return;
                }

                List<PrediccionMunicipio> prediccionMunicipioList = response.body();
                for (PrediccionMunicipio prediccionMunicipio : prediccionMunicipioList) {
                    String content = "";
                    content += prediccionMunicipio.toString() + "\n\n";
                    myJsonTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<PrediccionMunicipio>> call, Throwable t) {

            }
        });
    }
}