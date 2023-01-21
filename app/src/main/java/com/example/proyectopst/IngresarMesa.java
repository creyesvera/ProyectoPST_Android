package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_mesa);
        codigo= (EditText) findViewById(R.id.codigo);

    }


    public void cargar_mesa(View view) {
        int id_mesa = Integer.parseInt(codigo.getText().toString());

        Intent i = new Intent(IngresarMesa.this, Teclado.class );
        String server= getIntent().getExtras().getString("server");
        int port= getIntent().getExtras().getInt("port");
        int id = getIntent().getExtras().getInt("id"); //id de usuario

        requestQueue = Volley.newRequestQueue(IngresarMesa.this);

        //conectar ---- envía el id de usuario y el id de su mesa
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://"+server+":"+port+"/android/insert/INSERT INTO mesas(id,iduser) VALUES("+id_mesa+","+ id + ")",
                //"http://"+server+":"+port+"/android/"+id_mesa+"/"+ id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response != null){
                            i.putExtra("server", server);
                            i.putExtra("port", port);
                            i.putExtra("id_usuario",id);
                            i.putExtra("id_mesa", id_mesa);
                            //i.putExtra("usuario", usernameEditText.getText().toString());
                            startActivity(i);
                        }
                        /*{
                            int t= 120000;
                            CountDownTimer temporizador = new CountDownTimer(t, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    StringRequest request2 = new StringRequest(
                                            Request.Method.GET,
                                            "http://"+server+":"+port+"/android/SELECT jugar FROM mesas WHERE id=" + codigo,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response2) {

                                                    if(response2 != null){
                                                        if(Boolean.parseBoolean(response2)== Boolean.TRUE){
                                                            onFinish();
                                                        }

                                                    }

                                                    else {
                                                        //usernameEditText.setText("Error");
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

                                public void onFinish() {
                                    i.putExtra("server", server);
                                    i.putExtra("port", port);
                                    i.putExtra("id_usuario",id);
                                    i.putExtra("id_mesa", codigo.getText().toString());
                                    //i.putExtra("usuario", usernameEditText.getText().toString());
                                    startActivity(i);

                                }

                            };
                            temporizador.start();
                        }*/

                        else {
                            //usernameEditText.setText("Error");
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



}