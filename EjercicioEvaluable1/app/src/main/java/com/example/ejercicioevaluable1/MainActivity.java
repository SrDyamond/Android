package com.example.ejercicioevaluable1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double[] valores =new double[100];
    private int ultimaPosicionCalculada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for (int i=0; i <this.valores.length;i++){
            this.valores[i] = 0;
        }
        this.valores[0] =1;
        this.valores[1] =1;
        this.valores[2] =2;
        this.ultimaPosicionCalculada=2;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pulsarBoton(View view) {

        EditText entradaNumeros = (EditText) findViewById(R.id.numberInput);
        TextView resultadoOutput = (TextView) findViewById(R.id.resultado);

        if (entradaNumeros.getText().toString().trim().length() == 0) {
            resultadoOutput.setText(R.string.NingunaPosicionIntroducida);
        } else {
            int posicionIntroducida = Integer.parseInt(entradaNumeros.getText().toString()) -1;
            if (posicionIntroducida >= 0 && posicionIntroducida < 100) {
                if (valores[posicionIntroducida] == 0) {
                    Calculo.calcularFibo(this.valores, this.ultimaPosicionCalculada, posicionIntroducida);
                    this.ultimaPosicionCalculada = posicionIntroducida;
                }
                //visualizo texto
                // getResources().getString(R.string.ultimaPosicionCalc)
                resultadoOutput.setText(String.valueOf(valores[posicionIntroducida]));
            } else {
                resultadoOutput.setText(R.string.fueraRango);
            }
        }
    }
}