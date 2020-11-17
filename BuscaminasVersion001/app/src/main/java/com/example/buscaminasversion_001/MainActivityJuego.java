package com.example.buscaminasversion_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Random;


public class MainActivityJuego extends AppCompatActivity {
    private int filas =0, columnas=0,minas=0;
    private int[][] tabla;
    private ImageView[][] imagenes;
    public boolean destapado=false;
    boolean estado= Boolean.parseBoolean("0");
    //0Jugando
    //1Gano
    //2Perdio
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
        llenarMinas(filas,columnas,minas);
        crearTablero(filas,columnas);

    }

    private void crearTablero(int filas,int columnas){
        imagenes = new ImageView[filas][columnas];
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
                    //Pasaamos a traves de un string  la posicion en la tabla de esa imagen
                    imageView.setContentDescription(x + "#" + y);
                    imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.boton_sin_pulsar));
                    imageView.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            if(!destapado){
                                ImageView imageView = (ImageView) view;
                                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.bandera));
                            } else {

                                return false;
                            }
                            return true;
                        }
                    });
                    imageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            //recuperamos la view a una imagen view
                            ImageView imageView = (ImageView) view;
                            //obtenemos la descripcion que pusimos antes yla partimos en dos por el hastag
                            // y pillamos el primer elemto que nos devuelve y ese es nuestro x
                            int x = Integer.parseInt(((String) imageView.getContentDescription()).split("#")[0]);
                            int y = Integer.parseInt(((String) imageView.getContentDescription()).split("#")[1]);
                            //MINA
                            if (tabla[y][x] == 9) {
                                destapado=true;
                                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.mina2_1));
                                boolean estado = Boolean.parseBoolean("2");
                                if(estado==Boolean.parseBoolean("2")){
                                    Toast.makeText(MainActivityJuego.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                                    explosion(imageView);
                                    reiniciar();
                                }
                                //BLANCO
                            } else if (tabla[y][x] == 0){
                                destapado=true;
                                imageView.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.blanco));
                                //MIRO SI ES BLANCA
                                recorrerPerimetro(y,x,imagenes[y][x]);
                                //SI ES NUMERO LE PONGO IMAGEN
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

    //llenarMinas
    public void llenarMinas (int fila,int colu, int min){
        int filas=fila;
        int columnas=colu;
        int minas=min;
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
          //  System.out.println("\n");
        }

        desordenarArray(tabla);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tabla[i][j]== 0) {
                    int cant = contarBombasTocadas(i, j);
                    tabla[i][j]= cant;
                }
            }
        }

//ver array
        for (y=0;y<filas;y++) {
            for (x = 0; x < columnas; x++) {
                System.out.print(tabla[y][x]);
            }
            System.out.println("\n");
        }
    }
    //desornedar
    public static void desordenarArray(int[][] a) {
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
    //contarombasCerca
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
    //ponerNumero
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
    //recorrer perimetro
    private void recorrerPerimetro(int fil, int col, ImageView image) {
        if (fil >= 0 && fil < filas && col >= 0 && col < columnas) {
            if (tabla[fil][col]== 0) {
                image.setImageDrawable(ContextCompat.getDrawable(MainActivityJuego.this, R.drawable.blanco));
                destapado =true;
                tabla[fil][col]= 50;
                System.out.println(fil+"+"+col);
                System.out.println(tabla[fil][col]);
                recorrerPerimetro(fil, col + 1, imagenes[fil][col]);
                recorrerPerimetro(fil, col - 1, imagenes[fil][col]);
                recorrerPerimetro(fil + 1, col, imagenes[fil][col]);
                recorrerPerimetro(fil - 1, col, imagenes[fil][col]);
                recorrerPerimetro(fil - 1, col - 1, imagenes[fil][col]);
                recorrerPerimetro(fil - 1, col + 1, imagenes[fil][col]);
                recorrerPerimetro(fil + 1, col + 1, imagenes[fil][col]);
                recorrerPerimetro(fil + 1, col - 1, imagenes[fil][col]);
            } else if (tabla[fil][col]>= 1 && tabla[fil][col]< 9) {
                    ponerNumero(imagenes[fil][col],tabla[fil][col]);
                    destapado=true;
            }
        }
    }
    //sonido eplosion
    public void explosion(View v) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.explosion1);
        mp.start();
    }
    //atras
    public void atras(View v) {
        Intent intent2 = new Intent(v.getContext(), MainActivity.class);
        startActivityForResult(intent2, 0);
    }
    //reiniciar
    public void reiniciar(){
        /*
        llenarMinas(filas,columnas,minas);
        crearTablero(filas,columnas);
        
         */

    }

}
