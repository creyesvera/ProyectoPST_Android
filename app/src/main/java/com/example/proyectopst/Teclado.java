package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Teclado extends AppCompatActivity {

    private TextView tiempo;
    private int tiempoJuego;
    private EditText numero;
    private int puntaje_extra=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado);

        tiempo = (TextView)findViewById(R.id.tiempo);
        numero =(EditText) findViewById(R.id.numero);
        Thread hilo_tiempo= new Thread(new Runnable() {
            @Override
            public void run() {
                tiempoJuego =10;
                while(tiempoJuego>0){
                    try {
                        tiempo.setText(tiempoJuego + "s");
                        Thread.sleep(1000);
                        tiempoJuego-=1;
                    }catch(InterruptedException ex){
                        ex.printStackTrace();
                    }

                }
            /*if(tiempoJuego== 0 ){
                //después que termina el tiempo se cambia de activity y se envían los datos al servidor
                int numero_ingresado= Integer.parseInt(numero.getText().toString());
                int puntaje= puntaje_extra * numero_ingresado;

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
*/


            }

        });
        hilo_tiempo.start();

    }


    public void terminar_turno(View view){
        numero.setClickable(false);
        numero.setFocusable(false);
        puntaje_extra= tiempoJuego;

    }


}