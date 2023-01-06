package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.proyectopst.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    //se conecta a la base de datos

    private String server = "192.168.200.26";
    private int port = 8080;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    //se incia seción y se verifica la existencia del usuario
    public void inicio_sesion(View view) {
        //EditText et1 = (EditText) findViewById(R.id.editText); //no
        Intent i = new Intent(MainActivity.this, LoginActivity.class );
        i.putExtra("server", server);
        i.putExtra("port", port);
        startActivity(i);
    }


    //se crea el usuario y se añade a la base de datos
    public void crear_cuenta(View view) {
        //EditText et1 = (EditText) findViewById(R.id.editText); //no
        Intent i = new Intent(MainActivity.this, LoginActivity.class );
        //i.putExtra("direccion", et1.getText().toString());
        startActivity(i);
    }
}