package com.example.buscaminasversion_001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityJuego extends AppCompatActivity {
    private TextView niv;
    private int filas =0, columnas=0,minas=0,totalceldas=0;
    private LinearLayout contenedor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_juego);
        //Importamos texto de ActivityMain
        Bundle bundle = getIntent().getExtras();
        String fraseimportada=bundle.getString("frase");

        niv=(TextView)findViewById(R.id.nivel);
        niv.setText(fraseimportada.toString());
        contenedor=(LinearLayout)findViewById(R.id.container);
        this.crearTablero(fraseimportada);
    }
    public void atras(View v) {
        Intent intent2 = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(intent2, 0);
    }


    private void crearTablero(String fraseimportada){
        String fraseImportada=fraseimportada;
        if (fraseImportada=="Facil") {
            filas = 3;
            columnas = 3;
            minas = 2;
            totalceldas = 6;
        }
        if (fraseImportada=="Medio") {
            filas = 8;
            columnas = 8;
            minas = 30;
            totalceldas = 64;
        }
        if (fraseImportada=="Dificil") {
            filas = 16;
            columnas = 16;
            minas = 99;
            totalceldas = 256;
        }
        Toast.makeText(this, "Creando tablero", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < totalceldas; i++) {
            Button bn = new Button(getApplicationContext());
            bn.setId(1 + 2000); //Define ids
            bn.setMinHeight(0);
            bn.setMinimumHeight(0);
            bn.setPadding(0,0,0,0); //Elimina padding
            bn.setTextSize(14);
            //Crea contenedor con peso de 1 para cada elemento.
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
              bn.setLayoutParams(params);
            //Agrega vista a contenedor padre.
            contenedor.addView(bn);
        }


    }

}