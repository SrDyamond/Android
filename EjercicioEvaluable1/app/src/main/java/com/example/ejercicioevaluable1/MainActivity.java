package com.example.ejercicioevaluable1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[] valores =new int[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0; i <this.valores.length;i++){
            this.valores[i] = 0;
        }
    }

    public void pulsarBoton(View view) {
        EditText entradaNumeros = (EditText) findViewById(R.id.numberInput);
        TextView resultado = (TextView) findViewById(R.id.resultado);

        if (entradaNumeros.getText().toString().trim().length() == 0) {
            resultado.setText("No has introducido nada.");
        } else {
            int posicionIntroducida = Integer.parseInt(entradaNumeros.getText().toString());
            if (posicionIntroducida > 0 && posicionIntroducida <= 100) {
                // comprobamos si la posicion pedida ya está calculada
                if (valores[posicionIntroducida - 1] == 0) {
                    Calculo.calcularHasta(this.valores, posicionIntroducida);
                }
                resultado.setText("El número introducido es " + String.valueOf(valores[posicionIntroducida - 1]));
            } else {
                resultado.setText("Posición fuera de rango");
            }
        }
    }
}