package com.example.danielo.practica.Ficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.danielo.practica.R;

public class FicheroRaw extends AppCompatActivity {
    Button volver;
    EditText eRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichero_raw);

        volver = (Button)findViewById(R.id.botonVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FicheroRaw.this, Fichero.class);
                startActivity(intent);
            }
        });
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String text = b.getString("texto");
        eRaw = (EditText)findViewById(R.id.eTextRaw);
        eRaw.setText(text);
    }
}
