package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonRegister;  //Botón para acceder al activity de registro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRegister = findViewById(R.id.button_register);                //Asocio el objecto botón de registro con el botón del layout
        buttonRegister.setOnClickListener(new View.OnClickListener() {      //Asocio la función register al listener del botón
            @Override
            public void onClick(View v) {
                funcionRegister (v);
            }
        });


    }

    //Función que se ejecuta al pulsar el botón de Regístrate! Visto en videotutoría
    public void funcionRegister (View v){
        Toast.makeText(this,getString(R.string.toast_registro),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}