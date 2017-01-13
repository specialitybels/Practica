package com.example.danielo.practica.Ficheros;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danielo.practica.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class Fichero2 extends AppCompatActivity {

    Button guardar, volver;
    EditText titulo,contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichero2);

        volver = (Button)findViewById(R.id.botonVolver);
        titulo = (EditText)findViewById(R.id.nombreFichero);
        contenido = (EditText)findViewById(R.id.contenidoFichero);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fichero2.this, Fichero.class);
                startActivity(intent);
            }
        });

        guardar = (Button)findViewById(R.id.botonGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();
                Bundle b = i.getExtras();
                String memoria = b.getString("memoria");
                String ruta = "";
                if (Objects.equals(memoria, "interna")) {
                    try {
                        ruta = "/data/data/com.example.danielo.david.daniel.jordi.practica.Ficheros/files";//ruta que coge por defecto en memoria interna
                        OutputStreamWriter fout = new OutputStreamWriter(openFileOutput((titulo.getText().toString() + ".txt"), Context.MODE_PRIVATE));
                        fout.write(contenido.getText().toString());
                        fout.close();
                    } catch (Exception e) {
                        Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                    }
                }
                if (Objects.equals(memoria, "externa")) {
                    try
                    {
                        File ruta_sd = getExternalFilesDir(Environment.DIRECTORY_DCIM);
                        ruta = ruta_sd.getPath();
                        File f = new File(ruta, titulo.getText().toString() + ".txt");
                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f));

                        fout.write(contenido.getText().toString());
                        fout.close();
                    }
                    catch (Exception ex)
                    {
                        Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                    }
                }

                String s = "TODO OK!" +
                        "\nArchivo guardado en: " +
                        "\n"+ ruta +"/" + titulo.getText().toString()+".txt";//aqui esta la ruta!
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                s, Toast.LENGTH_SHORT);
                toast1.show();
            }
        });
    }
}