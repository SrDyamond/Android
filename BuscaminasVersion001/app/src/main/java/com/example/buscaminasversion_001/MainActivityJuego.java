package com.example.buscaminasversion_001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityJuego extends AppCompatActivity {
    private TextView niv;
    private int filas =0, columnas=0,minas=0,totalceldas=0,casilla=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_juego);
        //Importamos texto de ActivityMain
        Bundle bundle = getIntent().getExtras();
        String fraseimportada=bundle.getString("frase");

        niv=(TextView)findViewById(R.id.nivel);
        niv.setText(fraseimportada.toString());
        this.crearTablero(fraseimportada);
    }
    public void atras(View v) {
        Intent intent2 = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(intent2, 0);
    }


    private void crearTablero(String fraseimportada){
        String fraseImportada=fraseimportada;
        if (fraseImportada=="Facil") {
            filas = 8;
            columnas = 8;
            minas = 10;
            totalceldas = 64;
        }
        if (fraseImportada=="Medio") {
            filas = 16;
            columnas = 16;
            minas = 40;
            totalceldas = 256;
        }
        if (fraseImportada=="Dificil") {
            filas = 30;
            columnas = 16;
            minas = 99;
            totalceldas = 480;
        }
        Toast.makeText(this, "Creando tablero", Toast.LENGTH_SHORT).show();
    }

}