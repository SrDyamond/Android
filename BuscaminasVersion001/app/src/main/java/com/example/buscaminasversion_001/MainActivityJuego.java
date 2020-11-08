package com.example.buscaminasversion_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivityJuego extends AppCompatActivity {
    private int filas =0, columnas=0,minas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_juego);
        //Importamos texto de ActivityMain
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

    }
    public void atras(View v) {
        Intent intent2 = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(intent2, 0);
    }

    private void crearTablero(int filas,int columnas){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int buttonSize = screenWidth / filas;


        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        for (int y = 0; y < columnas; y++) {
            LinearLayout linearLayoutRow = new LinearLayout(this);
            LinearLayout.LayoutParams linearLayoutRowLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayoutRow.setLayoutParams(linearLayoutRowLp);
            linearLayoutRow.setOrientation(LinearLayout.HORIZONTAL);
            for (int x = 0; x < filas; x++) {
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams imageViewLp = new LinearLayout.LayoutParams(buttonSize, buttonSize);
                imageView.setLayoutParams(imageViewLp);
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.boton_pulsado));
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ImageView) view).setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_sin_pulsar));
                    }
                });
                linearLayoutRow.addView(imageView);
                //  this.gameLogic.addButton(x, y, imageView);
            }
            linearLayout.addView(linearLayoutRow);
        }
    }

}

