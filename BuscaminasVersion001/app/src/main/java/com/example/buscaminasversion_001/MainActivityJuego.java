package com.example.buscaminasversion_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Random;


public class MainActivityJuego extends AppCompatActivity {
    private int filas =0, columnas=0,minas=0;
    private int[][] tabla;
    private ImageView[][] imagenes;
    private boolean destapado=false;
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
                                destapado=true;
                                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.mina2_1));
                                boolean estado = Boolean.parseBoolean("2");
                                if(estado==Boolean.parseBoolean("2")){
                                    Toast.makeText(MainActivityJuego.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                                }
                            } else if (tabla[y][x] == 0){
                                destapado=true;
                                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.blanco));
                                recorrer(y,x);
                            } else if (tabla[y][x] >=1){
                                destapado=true;
                                ponerNumero(imagenes[y][x],tabla[y][x]);
                            }

                        }
                    });
                    linearLayoutRow.addView(imageView);
                    imagenes[y][x] = imageView;
                }
                linearLayout.addView(linearLayoutRow);
            }
        }

    public void llenarMinas (int [] valores){
        int filas=valores[0];
        int columnas=valores[1];
        int minas=valores[2];
        tabla = new int [filas][columnas];
        int y=0,x=0;
        int cont=minas;

        for (y=0;y<filas;y++) {
            for (x = 0; x < columnas; x++) {
                if (cont > 0) {
                    tabla[y][x] = 9;
                    cont = cont - 1;
                } else {
                    tabla[y][x] = 0;
                }
             //   System.out.print(tabla[y][x]);
            }
        //    System.out.println("\n");
        }

        bidimensionalArrayShuffle(tabla);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tabla[i][j]== 0) {
                    int cant = contarBombasTocadas(i, j);
                    tabla[i][j]= cant;
                }
            }
        }

        for (y=0;y<filas;y++) {
            for (x = 0; x < columnas; x++) {
                System.out.print(tabla[y][x]);
            }
               System.out.println("\n");
        }
    }

    public void ponerNumero(ImageView imagen, int i){
        switch (i) {
            case 1:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_1));
                break;
            case 2:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_2));
                break;
            case 3:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_3));
                break;
            case 4:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_4));
                break;
            case 5:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_5));
                break;
            case 6:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_6));
                break;
            case 7:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_7));
                break;
            case 8:
                imagen.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.boton_8));
                break;

        }
    }

    private void recorrer(int fil, int col) {
        if (fil >= 0 && fil < filas && col >= 0 && col < columnas) {
            if (tabla[fil][col]== 0) {
                destapado =true;
                tabla[fil][col]= 50;
                recorrer(fil, col + 1);
                recorrer(fil, col - 1);
                recorrer(fil + 1, col);
                recorrer(fil - 1, col);
                recorrer(fil - 1, col - 1);
                recorrer(fil - 1, col + 1);
                recorrer(fil + 1, col + 1);
                recorrer(fil + 1, col - 1);
            } else if (tabla[fil][col]>= 1
                    && tabla[fil][col]< 9) {
                    destapado=true;
            }
        }
    }

 



    int contarBombasTocadas(int fila, int columna) {
        int total = 0;
        if (fila - 1 >= 0 && columna - 1 >= 0) {
            if (tabla[fila - 1][columna - 1]== 9)
                total++;
        }
        if (fila - 1 >= 0) {
            if (tabla[fila - 1][columna]== 9)
                total++;
        }
        if (fila - 1 >= 0 && columna + 1 < columnas) {
            if (tabla[fila - 1][columna + 1]== 9)
                total++;
        }
        if (columna + 1 < columnas) {
            if (tabla[fila][columna + 1]== 9)
                total++;
        }
        if (fila + 1 < filas && columna + 1 < columnas) {
            if (tabla[fila + 1][columna + 1]== 9)
                total++;
        }
        if (fila + 1 < filas) {
            if (tabla[fila + 1][columna]== 9)
                total++;
        }
        if (fila + 1 < filas && columna - 1 >= 0) {
            if (tabla[fila + 1][columna - 1]== 9)
                total++;
        }
        if (columna - 1 >= 0) {
            if (tabla[fila][columna - 1]== 9)
                total++;
        }
        return total;
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
