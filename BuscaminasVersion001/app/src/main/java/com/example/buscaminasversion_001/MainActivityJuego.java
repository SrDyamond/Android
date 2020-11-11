package com.example.buscaminasversion_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;


public class MainActivityJuego extends AppCompatActivity {
    private int filas =0, columnas=0,minas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_juego);
        Intent intent = getIntent();
        int[]valores = intent.getIntArrayExtra("Array");
        try {
            assert valores != null;
            filas = valores[0];
            columnas = valores[1];
            minas = valores[2];
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            filas = 8;
            columnas = 8;
            minas = 10;
        }

        crearTablero(filas,columnas);
        desordenarArray(valores);

    }

    private void crearTablero(int filas,int columnas){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int buttonSize = screenWidth / filas;


        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        for (int y = 0; y < columnas; y++) {
            LinearLayout linearLayoutRow = new LinearLayout(this);
            LinearLayout.LayoutParams linearLayoutRowLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayoutRow.setLayoutParams(linearLayoutRowLp);
            linearLayoutRow.setOrientation(LinearLayout.HORIZONTAL);
            for (int x = 0; x < filas; x++) {
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams imageViewLp = new LinearLayout.LayoutParams(buttonSize, buttonSize);
                imageView.setLayoutParams(imageViewLp);
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.boton_sin_pulsar));
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ImageView) view).setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_pulsado));
                    }
                });
                linearLayoutRow.addView(imageView);
                //  this.gameLogic.addButton(x, y, imageView);
            }
            linearLayout.addView(linearLayoutRow);
        }
    }

    public void desordenarArray(int [] valores){
        int fila=valores[0];
        int columna=valores[1];
        int minas=valores[2];
        int tabla[][] = new int [fila][columna];
        int cont=minas;

        for (int x=0;x<fila;x++) {
            for (int y = 0; y < columna; y++) {
                if (cont > 0) {
                    tabla[x][y] = 9;
                    cont = cont - 1;
                }
                System.out.print(tabla[x][y] + "\t");
            }
            System.out.println("\n");
        }

    }

    public void atras(View v) {
        Intent intent2 = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(intent2, 0);
    }

}

