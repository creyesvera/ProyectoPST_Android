package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;

public class IngresarMesa extends AppCompatActivity {

    private RequestQueue requestQueue;
    private EditText codigo; // id de mesa
    private String id_mesa;
    private String id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_mesa);
        codigo = (EditText) findViewById(R.id.codigo);
        requestQueue = Volley.newRequestQueue(IngresarMesa.this);

    }
    public void crear_mesa(View view){
        Intent i = new Intent(IngresarMesa.this, Teclado.class);
        String server = getIntent().getExtras().getString("server");
        int port = getIntent().getExtras().getInt("port");
        id_usuario = String.valueOf(getIntent().getExtras().getInt("id"));
        obtener_id_mesa(codigo.getText().toString(), server, port); // id de mesa
        StringRequest request = new StringRequest(
            Request.Method.Post,
            "http://" + server + ":" + port + "/android/insert/INSERT INTO partidas (id_mesa, id_usuario, puntaje) VALUES ('" + id_mesa + "', '" + id_usuario + ", " + '0' +")",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                         // Handle the case where the insert is successful
                         if(response.equals("Partida creada")) {
                            Toast.makeText(getApplicationContext(), "Partida creada exitosamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al crear partida", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle the case where the code is incorrect or there is an error in the response
                    }
                }
            }
        );
        requestQueue.add(request);
    }

    public void obtener_id_mesa(final String code, final String server, final int port) {
        // Send a GET request to the server to obtain the id_mesa based on the code entered
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://" + server + ":" + port + "/android/SELECT id FROM mesas WHERE codigo = '" + code + "'",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            // If the code is correct, parse the id_mesa from the response
                            id_mesa = Integer.parseInt(response);
                            // Do something with the id_mesa, such as storing it in a variable or passing it to another method
                        } else {
                            // Handle the case where the code is incorrect or there is an error in the response
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the case where there is an error in the request
                    }
                }
        );
        requestQueue.add(request);
    }

}
