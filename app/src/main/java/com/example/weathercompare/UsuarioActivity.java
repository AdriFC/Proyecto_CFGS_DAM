package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UsuarioActivity extends AppCompatActivity {

    //Atributos
    TextView bienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        //Paso del objeto usuario desde la clase login
        Usuario usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");

        //Agregación del nombre de usuario al TextViev de bienvenida
        bienvenida = findViewById(R.id.Bienvenida);
        bienvenida.setText(bienvenida.getText().toString() + "\n" + usuario.getNombre());
        //System.out.println(usuario.toString());

    }


}