package com.example.danielo.practica.Intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.danielo.practica.R;

public class ActivityResultadoTelefono extends AppCompatActivity {
    TextView txtResultado;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_resultado_telefono);

        volver = (Button)findViewById(R.id.botonVolver);
        txtResultado = (TextView)findViewById(R.id.textoTelefono);
        Bundle b = getIntent().getExtras();
        txtResultado.setText(b.getString("Numero"));
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityResultadoTelefono.this, ActivityIntent.class);


                startActivity(intent);
            }
        });
    }
}
