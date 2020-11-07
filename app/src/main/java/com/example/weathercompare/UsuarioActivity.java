package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UsuarioActivity extends AppCompatActivity {

    //Atributos
    TextView bienvenida;
    Button buttonLogout;
    Button buttonComparar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        buttonComparar = findViewById(R.id.button_conparacion);
        buttonComparar.setOnClickListener(new View.OnClickListener() {      //Asocio la función login al listener del botón
            @Override
            public void onClick(View v) {
                funcionApi (v);
            }
        });




        //Paso del objeto usuario desde la clase login
        Usuario usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");

        //Agregación del nombre de usuario al TextViev de bienvenida
        bienvenida = findViewById(R.id.Bienvenida);
        bienvenida.setText(bienvenida.getText().toString() + "\n" + usuario.getNombre());
        //System.out.println(usuario.toString());

        //Asociación botón layout
        buttonLogout = findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lanzamiento de activity main limpiando la pila de activities (logout)
                Toast.makeText(UsuarioActivity.this,getString(R.string.toast_logout),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();

            }
        });

    }

    //Función que se ejecuta al pulsar el botón de comparar!
    public void funcionApi (View v){
        Toast.makeText(UsuarioActivity.this,"prueba",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UsuarioActivity.this, Comparacion_activity.class));
    }


}