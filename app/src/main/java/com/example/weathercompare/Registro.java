package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        email = findViewById(R.id.editTextLEmail);
        contraseña = findViewById(R.id.editTextLPassword);
        register = findViewById(R.id.button_login2);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        BaseDeDatos bd = new BaseDeDatos(this, "android", null, 1);
        if(bd.insertData(nombre.getText().toString(), email.getText().toString(), contraseña.getText().toString()))
        {
            Toast.makeText(this,(R.string.toast_registroExito),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,(R.string.toast_registroFallido),Toast.LENGTH_SHORT).show();
        }


        bd.getData();

        startActivity(new Intent(Registro.this, Login.class));
    }


}