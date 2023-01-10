package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TecladoNumerico extends AppCompatActivity {
    EditText Number = (EditText) findViewById(R.id.number);
    Button buttonClear = (Button) findViewById(R.id.button_clear);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClick(v);
            }
        });
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        String input = button.getText().toString();
        String currentText = Number.getText().toString();
        Number.setText(currentText + input);
    }

    public void onClearClick(View view) {
        Number.setText("");
    }



    public void onClearClickBinding(View view) {
            onClearClick(view);
        }

}