package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectopst.ui.login.LoginActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //se conecta a la base de datos

    private String server = "172.19.191.168";
    private int port = 8080;
    private RequestQueue requestQueue;
    private EditText usernameEditText;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        usernameEditText=(EditText)findViewById(R.id.editTextTextPersonName);
        passwordEditText=(EditText)findViewById(R.id.editTextTextPassword);

    }

    //se incia seción y se verifica la existencia del usuario
    public void inicio_sesion(View view) {
        //Consultar con la base de datos si el usuario existe
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://"+server+":"+port+"/android/SELECT id, puntaje, estrellas from usuarios WHERE nombre= '" + usernameEditText.getText().toString() + "' && contrasena= '" + passwordEditText.getText().toString() + "'",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response != null){
                            if(response.equals("Tabla vacía")){
                                usernameEditText.setText("usuario o contrasena incorrecta");
                            }
                            else {
                                String[] cadena = Arrays.stream(response.split("\\r\\n")).toArray(String[]::new);
                                String[] datos = Arrays.stream(cadena[3].split(" \\t\\t\\t| ")).toArray(String[]::new);
                                int id = Integer.parseInt(datos[0]);
                                int puntaje = Integer.parseInt(datos[2]);
                                int estrellas = Integer.parseInt(datos[4]);

                                //EditText et1 = (EditText) findViewById(R.id.nickname);
                                Intent i = new Intent(MainActivity.this, IngresarMesa.class);
                                i.putExtra("server", server);
                                i.putExtra("port", port);
                                i.putExtra("id", id);
                                i.putExtra("puntaje", puntaje);
                                i.putExtra("estrellas", estrellas);
                                startActivity(i);
                            }

                        }

                        else {
                             usernameEditText.setText("Error");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);

    }


    //se crea el usuario y se añade a la base de datos
    public void crear_cuenta(View view) {
        //EditText et1 = (EditText) findViewById(R.id.nickname);
        Intent i = new Intent(MainActivity.this, LoginUser.class );

        i.putExtra("server", server);
        i.putExtra("port", port);
        //i.putExtra("direccion", et1.getText().toString());
        startActivity(i);
    }
}