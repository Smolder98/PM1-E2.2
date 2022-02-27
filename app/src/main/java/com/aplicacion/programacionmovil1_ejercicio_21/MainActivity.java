package com.aplicacion.programacionmovil1_ejercicio_21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRetrofit, btnVolley;
    Intent intentNavegar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setListeners();
    }

    private void init(){

        intentNavegar = null;

        btnRetrofit = (Button) findViewById(R.id.btnRetrofit);
        btnVolley = (Button) findViewById(R.id.btnVolley);
    }

    private void setListeners(){
        btnRetrofit.setOnClickListener(v -> irRetrofit());
        btnVolley.setOnClickListener(v -> irVolley());
    }

    private void irRetrofit(){

        intentNavegar = new Intent(getApplicationContext(), ActivityRetrofit.class);
        startActivity(intentNavegar);
    }

    private void irVolley(){
        intentNavegar = new Intent(getApplicationContext(), ActivityVolley.class);
        startActivity(intentNavegar);
    }
}