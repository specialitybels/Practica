package com.example.danielo.practica.Principal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.danielo.practica.Ficheros.Fichero;
import com.example.danielo.practica.Intent.ActivityIntent;
import com.example.danielo.practica.R;
import com.example.danielo.practica.SharedPrefernces.SharedPrincipal;
import com.example.danielo.practica.Sqlite.SqlitePrincipal;

public class MainActivity extends AppCompatActivity {

    Button pasar,botonshared,bbdd,fichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pasar = (Button)findViewById(R.id.botonIntent);


        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityIntent.class);
                Bundle b =new Bundle();
                startActivity(intent);
            }
        });


        botonshared = (Button)findViewById(R.id.botonSharedPreferences);
        botonshared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SharedPrincipal.class);

                startActivity(intent);
            }
        });

        bbdd = (Button)findViewById(R.id.botonSQLite);


        bbdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SqlitePrincipal.class);
                startActivity(intent);
            }
        });

        fichero = (Button)findViewById(R.id.botonFicheros);


        fichero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Fichero.class);
                startActivity(intent);
            }
        });
    }


}
