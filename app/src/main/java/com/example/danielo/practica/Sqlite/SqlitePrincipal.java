package com.example.danielo.practica.Sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.danielo.practica.Principal.MainActivity;
import com.example.danielo.practica.R;

public class SqlitePrincipal extends AppCompatActivity {
    Button bVolver;
    Button bCrear;
    EditText eNombre;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_principal);
        bVolver = (Button)findViewById(R.id.btnVolver);
        bCrear = (Button)findViewById(R.id.btnCrear);
        eNombre = (EditText)findViewById(R.id.eNombre);
        //BASE DE DATOS

        //BOTON VOLVER
        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqlitePrincipal.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //BOTON CREAR
        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqlitePrincipal.this, SqliteProducto.class);
                Bundle b = new Bundle();

                nombre = eNombre.getText().toString();
                b.putString("Nombre",nombre);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }


}
