package com.example.danielo.practica.Intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.danielo.practica.Principal.MainActivity;
import com.example.danielo.practica.R;

public class ActivityIntent extends AppCompatActivity {
    String num;
    EditText txtEnvio;
    Button volver,enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_principal);

        enviar = (Button)findViewById(R.id.botonEnviar);
        txtEnvio = (EditText)findViewById(R.id.textoTelefono);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityIntent.this, ActivityResultadoTelefono.class);
                Bundle b =new Bundle();
                num = txtEnvio.getText().toString();
                b.putString("Numero",num);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        volver = (Button)findViewById(R.id.botonVolver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ActivityIntent.this, MainActivity.class);
                Bundle b1 =new Bundle();
                startActivity(intent1);
            }
        });
    }
}
