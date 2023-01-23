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
    private int id_mesa;
    private int id_usuario;
    private int repuesta;
    private String server;
    private int port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_mesa);
        codigo = (EditText) findViewById(R.id.codigo);
        requestQueue = Volley.newRequestQueue(IngresarMesa.this);
        Intent i = new Intent(IngresarMesa.this, Teclado.class);
        server = getIntent().getExtras().getString("server");
        port = getIntent().getExtras().getInt("port");

    }
    public void crear_mesa(View view){
        id_usuario = getIntent().getExtras().getInt("id");
        obtener_id_mesa(); // id de mesa

    }

    public void obtener_id_mesa() {
        // Send a GET request to the server to obtain the id_mesa based on the code entered
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://" + server + ":" + port + "/android/SELECT id FROM mesas WHERE codigo = '" +  codigo.getText().toString()+ "')",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            if (response.equals("Tabla vacia")){
                                Toast.makeText(IngresarMesa.this, "Mesa no encontrada", Toast.LENGTH_SHORT).show();                        
                            } else {
                                String[] cadena = new String[0];
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                    cadena = Arrays.stream(response.split("\\r\\n")).toArray(String[]::new);
                                }
                                String[] datos = new String[0];
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                    datos = Arrays.stream(cadena[3].split("\\t\\t\\t| ")).toArray(String[]::new);
                                } 

                                id_mesa = Integer.parseInt(datos[1]);
                                repuesta = Integer.parseInt(datos[2]);
                                Toast.makeText(IngresarMesa.this, "id_mesa: " + id_mesa, Toast.LENGTH_SHORT).show();
                                Toast.makeText(IngresarMesa.this, "repuesta: " + repuesta, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(IngresarMesa.this, "Error al obtener id_mesa", Toast.LENGTH_SHORT).show();
                        }   
                    }
                }         
        );
        requestQueue.add(request);
    }

}
