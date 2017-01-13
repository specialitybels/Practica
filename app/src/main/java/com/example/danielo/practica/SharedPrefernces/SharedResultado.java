package com.example.danielo.practica.SharedPrefernces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.danielo.practica.R;

public class SharedResultado extends AppCompatActivity {
    TextView resultat, operation;
    float resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_resultado_pantalla);

        resultat = (TextView) findViewById(R.id.result);
        operation = (TextView) findViewById(R.id.operation);
        Bundle b = getIntent().getExtras();
        resultado = (float) b.getDouble("res");
        resultat.setText(String.valueOf(b.getDouble("res")));
        operation.setText(b.getString("oper"));
    }

    public void guardar(View v) {
        SharedPreferences prefs =
                getSharedPreferences("PreferenciasCalculadora", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("resultatAnterior",(int)resultado);
        editor.commit();
        Intent intent = new Intent(SharedResultado.this, SharedPrincipal.class);
        startActivity(intent);

    }
}
