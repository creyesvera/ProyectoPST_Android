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

public class IngresarMesa extends AppCompatActivity {

    private RequestQueue requestQueue;
    private EditText codigo; // id de mesa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_mesa);

    }


    public void cargar_mesa(View view) {
        codigo= (EditText) findViewById(R.id.codigo);

        //conectar ---- envía el id de usuario y el id de su mesa
/*
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://"+server+":"+port+"/android",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response != null)
                            consulta.setText(response);
                        else
                            consulta.setText("Error desconocido en la consulta");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);
        */
        Intent i = new Intent(IngresarMesa.this, TecladoNumerico.class );
        String server= getIntent().getExtras().getString("server");
        int port= getIntent().getExtras().getInt("port");
        String usuario= getIntent().getExtras().getString("usuario");
        i.putExtra("server", server);
        i.putExtra("port", port);
        i.putExtra("usuario",usuario);
        i.putExtra("id_mesa", codigo.getText().toString());
        //i.putExtra("usuario", usernameEditText.getText().toString());
        startActivity(i);
    }

}