package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUser extends AppCompatActivity {
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        username = (EditText)findViewById(R.id.nickname);
        password = (EditText)findViewById(R.id.password);
    }
    private void login(View view) {
        /*
        // Verifica si el nombre de usuario y la contraseña son válidos
        if (userName.equals("user") && pass.equals("password")) {
            // Inicia sesión exitosamente
            Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            // Redirige al usuario a la siguiente actividad
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            // Inicio de sesión fallido
            Toast.makeText(getApplicationContext(), "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
        */

    }

}