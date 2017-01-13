package com.example.danielo.practica.Ficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.danielo.practica.Principal.MainActivity;
import com.example.danielo.practica.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fichero extends AppCompatActivity {

    Button volver, memInt, memExt,raw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichero);

        volver = (Button)findViewById(R.id.botonVolver);


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fichero.this, MainActivity.class);
                startActivity(intent);
            }
        });

        memInt = (Button)findViewById(R.id.ficheroMemInterna);


        memInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fichero.this, Fichero2.class);
                Bundle b =new Bundle();
                b.putString("memoria","interna");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        memExt = (Button)findViewById(R.id.ficheroMemExterna);

        memExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fichero.this, Fichero2.class);
                Bundle b =new Bundle();
                b.putString("memoria","externa");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        raw = (Button)findViewById(R.id.ficheroRAW);

        raw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    InputStream fraw =
                            getResources().openRawResource(R.raw.fichero_raw);
                    BufferedReader brin =
                            new BufferedReader(new InputStreamReader(fraw));
                    String linea = brin.readLine();

                    Intent intent = new Intent(Fichero.this, FicheroRaw.class);
                    Bundle b =new Bundle();
                    b.putString("texto",linea);
                    intent.putExtras(b);
                    startActivity(intent);

                    fraw.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde recurso raw");
                }
            }
        });
    }
}
