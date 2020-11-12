package com.example.buscaminasversion_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;


public class MainActivityJuego extends AppCompatActivity {
    private int filas =0, columnas=0,minas=0;
    private int[][] tabla;
    private ImageView[][] imagenes;
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

        llenarMinas(valores);
        bidimensionalArrayShuffle(tabla);
        crearTablero(filas,columnas);

    }

    private void crearTablero(int filas,int columnas){
        imagenes = new ImageView[filas][columnas];
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int buttonSize = screenWidth / filas;
        boolean estado= Boolean.parseBoolean("0");
        //0Jugando
        //1Gano
        //2Perdio

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
                    //Pasaamos a traves de un string  la posicion en la tabla de esa imagen
                    imageView.setContentDescription(x + "#" + y);
                    imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.boton_sin_pulsar));
                    imageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            //recuperamos la view a una imagen view
                            ImageView imageView = (ImageView) view;
                            //obtenemos la descripcion que pusimos antes yla partimos en dos por el hastag
                            // y pillamos el primer elemto que nos devuelve y ese es nuestro x
                            int x = Integer.parseInt(((String) imageView.getContentDescription()).split("#")[0]);
                            int y = Integer.parseInt(((String) imageView.getContentDescription()).split("#")[1]);
                            if (tabla[y][x] == 9) {
                                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.mina));
                                boolean estado = Boolean.parseBoolean("2");
                                if(estado==Boolean.parseBoolean("2")){
                                    Toast.makeText(MainActivityJuego.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                                }
                            } else
                                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_pulsado));
                        }
                    });
                    linearLayoutRow.addView(imageView);
                    imagenes[y][x] = imageView;
                    System.out.println(imagenes[y][x]);
                }
                linearLayout.addView(linearLayoutRow);
            }
        }

    public void llenarMinas (int [] valores){
        int filas=valores[0];
        int columnas=valores[1];
        int minas=valores[2];
        tabla = new int [filas][columnas];
        int cont=minas;

        for (int y=0;y<filas;y++) {
            for (int x = 0; x < columnas; x++) {
                if (cont > 0) {
                    tabla[y][x] = 9;
                    cont = cont - 1;
                } else {
                    tabla[y][x] = 0;
                }
                System.out.print(tabla[y][x]);
            }
            System.out.println("\n");
        }

    }

    //desornedar
    public static void bidimensionalArrayShuffle(int[][] a) {
        Random random = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }

    public void atras(View v) {
        Intent intent2 = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(intent2, 0);
    }

}
