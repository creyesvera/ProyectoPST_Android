package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Teclado extends AppCompatActivity {
    private Thread hilo_tiempo;
    private TextView tiempo;
    private int tiempoJuego;
    private EditText numero;
    private int puntaje_extra=0;
    private int num_bddatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado);

        tiempo = (TextView)findViewById(R.id.tiempo);
        numero =(EditText) findViewById(R.id.numero);

        CountDownTimer temporizador = new CountDownTimer(10000, 1000) {
            TextView textView = findViewById(R.id.tiempo);

            public void onTick(long millisUntilFinished) {
                // Actualiza el TextView con la cuenta restante
                textView.setText(String.valueOf(millisUntilFinished / 1000));
                tiempoJuego = Integer.parseInt(textView.getText().toString());

            }

            public void onFinish() {
                // Cuando el temporizador llegue a cero, actualiza el TextView con un mensaje
                //textView.setText("¡Temporizador terminado!");
                int numero_ingresado= Integer.parseInt(numero.getText().toString());
                //verificar que el numero ingresado es igual al de la base de datos
                //codigo

                int puntaje= puntaje_extra + numero_ingresado;

                Intent i = new Intent(Teclado.this, JuegoFinalizado.class );
                String server= getIntent().getExtras().getString("server");
                int port= getIntent().getExtras().getInt("port");
                String usuario= getIntent().getExtras().getString("usuario");
                String id_mesa= getIntent().getExtras().getString("id_mesa");
                i.putExtra("server", server);
                i.putExtra("port", port);
                i.putExtra("usuario",usuario);
                i.putExtra("id_mesa", id_mesa);
                i.putExtra("puntaje", puntaje);
                //i.putExtra("usuario", usernameEditText.getText().toString());
                startActivity(i);

            }
        };
        temporizador.start();


    }


    public void terminar_turno(View view){
        numero.setClickable(false);
        numero.setFocusable(false);
        puntaje_extra= tiempoJuego;

    }

}