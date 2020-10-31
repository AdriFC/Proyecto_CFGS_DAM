package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        //Asociación de los objetos con los elementos del layout
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

    //Función que realiza el registro de usuario en la base de datos
    private void registerUser(){
        BaseDeDatos bd = new BaseDeDatos(this, "android", null, 1);
        if(validarEmail(email.getText().toString())) {
            if(validarContrasenia(contraseña.getText().toString())){
                //Insección de usuario en la base de datos
                bd.insertData(nombre.getText().toString(), email.getText().toString(), contraseña.getText().toString());
                Toast.makeText(this, (R.string.toast_registroExito), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registro.this, Login.class));
            }else{
                Toast.makeText(this,(R.string.toast_passwordIncorrecto),Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this,(R.string.toast_emailIncorrecto),Toast.LENGTH_LONG).show();
        }


        //bd.getData();


    }

    //Función para validar el email
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        /*if(pattern.matcher(email).matches()){
            System.out.println("email válido: " + email);
        }else{
            System.out.println("email no válido: " + email);
        }*/
        return pattern.matcher(email).matches();
    }

    //Función para validar la contraseña
    public boolean validarContrasenia(String password){

        // Expresión que valida la contraseña
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,20}$";

        //Compilación de la expresión
        Pattern p = Pattern.compile(regex);

        //Si la contraseña está vacía, devuelve falso
        if (password == null) {
            return false;
        }

        return p.matcher(password).matches();

    }

}