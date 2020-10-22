package com.example.miprimerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int matematicas=5;
        int quimica =5;
        int fisica =5;
        int promedio=0;

        promedio=(matematicas+quimica+fisica) / 3;

        if(promedio>=6){
            Toast.makeText(this, "Aprobado", Toast.LENGTH_SHORT).show();
        } else if(promedio <=5){
            Toast.makeText(this, "Suspenso", Toast.LENGTH_SHORT).show();
        }

    }
}