package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    //Atributos
    EditText email;
    EditText contraseña;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextLEmail);
        contraseña = findViewById(R.id.editTextLPassword);
        login = findViewById(R.id.button_login2);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    //Función que realiza el login
    private void loginUser() {
        BaseDeDatos bd = new BaseDeDatos(this, "android", null, 1);

        Usuario usuario = bd.checkLogin(email.getText().toString(), contraseña.getText().toString());

        // No se ha encontrado usuario con ese email
        if(usuario.getId() == 0) {
            Toast.makeText(this,(R.string.toast_loginInvalido),Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,(R.string.toast_loginValido),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UsuarioActivity.class);
            intent.putExtra("usuario",usuario);
            startActivity(intent);

            /*Intent intent = new Intent(Activity_Origen.this, Activity_Destino.class);
            intent.putExtra("parametro", "string");
            startActivity(intent);*/
        }
    }
}