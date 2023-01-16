package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;


public class TecladoNumerico extends AppCompatActivity {
    EditText number;
    Button buttonClear;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    private TextView time;
    private int turnos;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teclado_numerico);
        time = (TextView)findViewById(R.id.time);
        number = (EditText) findViewById(R.id.number);
        buttonClear = (Button) findViewById(R.id.button_clear);

        // Obtener la instancia de SharedPreferences
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        // Obtener el valor de turnos almacenado
        turnos = sharedPref.getInt("turnos", 3);

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                time.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish() {
                // Disminuir un turno y verificar
                if (turnos > 0) {
                    turnos-=1;
                    Toast.makeText(getApplicationContext(), "Turnos restantes: "+turnos, Toast.LENGTH_SHORT).show();
                    // Guardar el valor de turnos en SharedPreferences
                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("turnos", turnos);
                    editor.apply();
                    number.setText("");
                    recreate();
                } else {
                    Toast.makeText(getApplicationContext(), "Juego terminado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), JuegoFinalizado.class);
                    startActivity(intent);
                }
            }
        }.start();

        button0 = (Button) findViewById(R.id.button_0);
        button1 = (Button) findViewById(R.id.button_1);
        button2 = (Button) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);
        button4 = (Button) findViewById(R.id.button_4);
        button5 = (Button) findViewById(R.id.button_5);
        button6 = (Button) findViewById(R.id.button_6);
        button7 = (Button) findViewById(R.id.button_7);
        button8 = (Button) findViewById(R.id.button_8);
        button9 = (Button) findViewById(R.id.button_9);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick();
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }
        });
    }

    private void onNumberClick(View v) {
        Button button = (Button) v;
        String number = button.getText().toString();
        this.number.setText(this.number.getText() + number);
    }

    public void onClearClick() {
        number.setText("");
    }

}