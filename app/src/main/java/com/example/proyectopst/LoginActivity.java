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

public class LoginActivity extends AppCompatActivity {

    private String server;
    private int port;
    private RequestQueue requestQueue;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        requestQueue = Volley.newRequestQueue(LoginActivity.this);

        usernameEditText = (EditText)findViewById(R.id.userName);
        passwordEditText = (EditText)findViewById(R.id.pass);
    }

    public void login(View view) { //el usuario se registra
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://"+server+":"+port+"/android/SELECT id, puntaje, estrellas from usuarios WHERE nombre= '" + usernameEditText.getText().toString() +"'",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response != null){
                            if (response.equals("Tabla vacía")){

                                StringRequest request2 = new StringRequest(
                                        Request.Method.GET,
                                        "http://"+server+":"+port+"/android/insert/INSERT INTO usuarios(nombre,contrasena,puntaje,estrellas) VALUES('" + usernameEditText.getText().toString()+"','" +passwordEditText.getText().toString() +"',0,0);",
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response2) {

                                                if(response2 != null){
                                                    if (response2.equals("Tabla vacía")){

                                                    }
                                                    else{
                                                        Intent i = new Intent(LoginActivity.this, IngresarMesa.class);
                                                        i.putExtra("server", server);
                                                        i.putExtra("port", port);
                                                        i.putExtra("id", 0);
                                                        i.putExtra("puntaje", 0);
                                                        i.putExtra("estrellas", 0);
                                                        startActivity(i);
                                                    }


                                                }

                                                else {

                                                    usernameEditText.setText("usuario ya existe");
                                                }

                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        }
                                );
                                requestQueue.add(request2);

                            }
                            else{
                                usernameEditText.setText("usuario ya existe");
                            }


                        }

                        else {

                            usernameEditText.setText("usuario ya existe");
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