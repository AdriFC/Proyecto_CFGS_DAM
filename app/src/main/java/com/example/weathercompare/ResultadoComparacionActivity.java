package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import Interface.JsonPlaceHolderApi;
import Modelo.Model200;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultadoComparacionActivity extends AppCompatActivity {

    //Atributo del TextView para recibir la información
    private TextView myJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadocomparacion);

        //Enlace del atributo con el textView del layout
        myJsonTextView = findViewById(R.id.JsonTextView);
        getPosts();
    }

    //Método para traer los datos
    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opendata.aemet.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Se llama a la interfaz
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        String url = "opendata/api/prediccion/especifica/municipio/diaria/" + "36039" + "/?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZHJpODZ2aWdvQGdtYWlsLmNvbSIsImp0aSI6IjJkNzgzYjhhLTFjMTUtNGJjNS04ZmJkLTMwZmY4NWM2NWUyNSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjAzMDE5NTg0LCJ1c2VySWQiOiIyZDc4M2I4YS0xYzE1LTRiYzUtOGZiZC0zMGZmODVjNjVlMjUiLCJyb2xlIjoiIn0.a1IIOmDUM1FI6neNmgLeT728iLAKa26mxia-Oe5sOWs";
        Call<Model200> call = jsonPlaceHolderApi.getPosts(url);
        call.enqueue(new Callback<Model200>() {
            @Override
            public void onResponse(Call<Model200> call, Response<Model200> response) {
                if(!response.isSuccessful()){
                    myJsonTextView.setText("código: " + response.code());
                    return;
                }

                Model200 post = response.body();
                /*for (Posts post: postsList){
                    String content = "";
                    content += "userId: " + post.getUserId() + "\n";
                    content += "id: " + post.getId() + "\n";
                    content += "title: " + post.getTitle() + "\n";
                    content += "body: " + post.getBody() + "\n\n";
                    myJsonTextView.append(content);
                }*/
                String content = "";
                content += post.toString() + "\n\n";
                myJsonTextView.append(content);
            }

            @Override
            public void onFailure(Call<Model200> call, Throwable t) {
                myJsonTextView.setText(t.getMessage());
            }
        });
    }
}