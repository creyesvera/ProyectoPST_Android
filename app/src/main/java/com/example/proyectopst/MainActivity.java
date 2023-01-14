package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.proyectopst.ui.login.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //se conecta a la base de datos

    private String server = "192.168.200.26";
    private int port = 8080;
    // definir imagenes
    private ImageView imageSelect;
    private ImageView img1, img2, img3, img4, img5, img6;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Arreglo de ImageView
        ImageView[] imageViews = {
                (ImageView) findViewById(R.id.img1),
                (ImageView) findViewById(R.id.img2),
                (ImageView) findViewById(R.id.img3),
                (ImageView) findViewById(R.id.img4),
                (ImageView) findViewById(R.id.img5),
                (ImageView) findViewById(R.id.img6)
        };

        imageSelect = (ImageView) findViewById(R.id.imgSelect);


        // generar Imagenes
        // Lista de nombres de imágenes
        String[] imageNames = {"a", "b", "c", "d", "f","g","h","i","j","k"};
        ArrayList<Integer> l_index = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            // Generar índice aleatorio
            int randomIndex = (int) (Math.random() * imageNames.length);
            // validar si la imagen ya ha sido generada
            while (l_index.contains(randomIndex)){
                randomIndex = (int) (Math.random() * imageNames.length);
            }
            l_index.add(randomIndex);

            // Obtener nombre de imagen aleatoria
            String randomImageName = imageNames[randomIndex];

            // Obtener id de recurso de imagen
            int imageResourceId = getResources().getIdentifier(randomImageName, "drawable", getPackageName());

            // Establecer imagen en ImageView
            imageViews[i].setImageResource(imageResourceId);
            // Establecer tag del ImageView con el id de recurso de la imagen
            imageViews[i].setTag(imageResourceId);

            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Acciones a realizar al hacer click en la imagen
                    int tag = (int) view.getTag();
                    // Establecer imagen seleccionada en ImageSelect
                    imageSelect.setImageResource(tag);
                }
            });
        }
    }

    //se incia seción y se verifica la existencia del usuario
    public void inicio_sesion(View view) {
        //EditText et1 = (EditText) findViewById(R.id.nickname);
        Intent i = new Intent(MainActivity.this, IngresarMesa.class );
        i.putExtra("server", server);
        i.putExtra("port", port);
        startActivity(i);
    }


    //se crea el usuario y se añade a la base de datos
    public void crear_cuenta(View view) {
        //EditText et1 = (EditText) findViewById(R.id.nickname);
        Intent i = new Intent(MainActivity.this, TecladoNumerico.class );
        //i.putExtra("direccion", et1.getText().toString());
        startActivity(i);
    }
}