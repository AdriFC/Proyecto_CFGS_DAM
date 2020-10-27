package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class Registro extends AppCompatActivity {

    //Atributos
    EditText nombre;
    EditText email;
    EditText contraseña;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.editTextRName);
        email = findViewById(R.id.editTextTextREmail);
        contraseña = findViewById(R.id.editTextRPassword);
        register = findViewById(R.id.button_register2);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        BaseDeDatos bd = new BaseDeDatos(this, "android", null, 1);
        bd.insertData(nombre.getText().toString(), email.getText().toString(), contraseña.getText().toString());
        bd.getData();
    }


}