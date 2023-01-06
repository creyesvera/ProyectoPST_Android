package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JuegoFinalizado extends AppCompatActivity {
    private TextView text_puntaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_finalizado);

        text_puntaje= (TextView) findViewById(R.id.puntaje);

       // Intent i = new Intent(JuegoFinalizado.this, Te.class );
        String server= getIntent().getExtras().getString("server");
        int port= getIntent().getExtras().getInt("port");
        String usuario= getIntent().getExtras().getString("usuario");
        String id_mesa= getIntent().getExtras().getString("id_mesa");
        int puntaje = getIntent().getExtras().getInt("puntaje");
        /*
        i.putExtra("server", server);
        i.putExtra("port", port);
        i.putExtra("usuario",usuario);
        i.putExtra("id_mesa", id_mesa);
        i.putExtra("puntaje", puntaje);
        */
        text_puntaje.setText(usuario + "   " + puntaje + " pts");

        //i.putExtra("usuario", usernameEditText.getText().toString());
        //startActivity(i);
    }

    public void volver_inicio(View view) {
        Intent i = new Intent(JuegoFinalizado.this, IngresarMesa.class );
        startActivity(i);
    }
}