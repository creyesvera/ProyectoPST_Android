package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectopst.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // se conecta a la base de datos

    private String server = "192.168.100.6";
    private int port = 8080;
    // definir imagenes
    private ImageView imageSelect;
    // login
    private RequestQueue requestQueue;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private String avatar = "";

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
        String[] imageNames = { "a", "b", "c", "d", "f", "g", "h", "i", "j", "k" };
        ArrayList<Integer> l_index = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            // Generar índice aleatorio
            int randomIndex = (int) (Math.random() * imageNames.length);
            // validar si la imagen ya ha sido generada
            while (l_index.contains(randomIndex)) {
                randomIndex = (int) (Math.random() * imageNames.length);
            }
            l_index.add(randomIndex);
            requestQueue = Volley.newRequestQueue(MainActivity.this);

            usernameEditText = (EditText) findViewById(R.id.editTextTextPersonName);
            passwordEditText = (EditText) findViewById(R.id.editTextTextPassword);

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
                    // Obtener el nombre de la imagen seleccionada
                    avatar = randomImageName;
                    Toast.makeText(getApplicationContext(), "img: "
                            + avatar, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // se incia seción y se verifica la existencia del usuario
    public void inicio_sesion(View view) {
        // Verificar si los campos de usuario y contraseña están llenos
        if (usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
        } else if (avatar.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor seleccione una imagen", Toast.LENGTH_SHORT).show();
        } else {
            // Consultar con la base de datos si el usuario existe
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    "http://" + server + ":" + port
                            + "/android/SELECT id, puntaje, estrellas from usuarios WHERE nombre= '"
                            + usernameEditText.getText().toString() + "' && contrasena= '"
                            + passwordEditText.getText().toString() + "'",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response != null) {
                                if (response.equals("Tabla vacía")) {
                                    usernameEditText.setText("usuario o contrasena incorrecta");
                                } else {
                                    String[] cadena = new String[0];
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                        cadena = Arrays.stream(response.split("\\r\\n")).toArray(String[]::new);
                                    }
                                    String[] datos = new String[0];
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                        datos = Arrays.stream(cadena[3].split(" \\t\\t\\t| ")).toArray(String[]::new);
                                    }
                                    int id = Integer.parseInt(datos[0]);
                                    int puntaje = Integer.parseInt(datos[2]);
                                    int estrellas = Integer.parseInt(datos[4]);

                                    // EditText et1 = (EditText) findViewById(R.id.nickname);
                                    Intent i = new Intent(MainActivity.this, IngresarMesa.class);
                                    i.putExtra("server", server);
                                    i.putExtra("port", port);
                                    i.putExtra("id", id);
                                    i.putExtra("puntaje", puntaje);
                                    i.putExtra("estrellas", estrellas);
                                    i.putExtra("avatar", avatar);
                                    startActivity(i);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "El usuario no se encuentra en la base de datos", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            requestQueue.add(request);
        }
    }

    // se crea el usuario y se añade a la base de datos
    public void crear_cuenta(View view) {
        // Verificar si los campos de usuario y contraseña están llenos
        if (usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
        } else if (avatar.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor seleccione una imagen", Toast.LENGTH_SHORT).show();
        } else {
            // Consultar si el usuario ya existe en la tabla de usuarios
            StringRequest request_ver = new StringRequest(
                    Request.Method.GET,
                    "http://" + server + ":" + port + "/android/SELECT id from usuarios WHERE nombre= '"
                            + usernameEditText.getText().toString() + "'",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response != null) {
                                if (response.equals("Tabla vacía")) {
                                    // Si el usuario no existe, subir los datos a la tabla de usuarios
                                    crear_usuario();
                                } else {
                                    Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT)
                                            .show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Error al crear cuenta", Toast.LENGTH_SHORT)
                                        .show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            requestQueue.add(request_ver);
        }
    }
    public void crear_usuario(){
        StringRequest request_new = new StringRequest(
                Request.Method.POST,
                "http://" + server + ":" + port
                        + "/android/insert/INSERT INTO usuarios (nombre, contrasena, avatar) VALUES ('"
                        + usernameEditText.getText().toString() + "', '"
                        + passwordEditText.getText().toString() + "', '" + avatar + "')",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {
                            if (response.equals("Usuario creado")) {
                                Toast.makeText(getApplicationContext(),
                                                "Cuenta creada exitosamente", Toast.LENGTH_SHORT)
                                        .show();
                            } else {
                                Intent i = new Intent(MainActivity.this, IngresarMesa.class);
                                i.putExtra("server", server);
                                i.putExtra("port", port);
                                i.putExtra("nombre", usernameEditText.getText().toString());
                                i.putExtra("puntaje", 0);
                                i.putExtra("estrellas", 0);
                                i.putExtra("avatar", avatar);
                                startActivity(i);

                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Error al crear cuenta mundo",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(request_new);

    }
}