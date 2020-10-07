package com.example.ejercicioevaluable1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pulsarBoton(View view) {
        EditText numberInput = findViewById(R.id.numberInput);
        TextView resultado = (TextView) findViewById(R.id.resultado);

        if (numberInput.getText().toString().trim().length() == 0) {
            resultado.setText("No has introducido nada.");
        } else {
            // String numeroIntroducidoString = entradaNumeros.getText().toString();
            int numeroIntroducidoInt = Integer.parseInt(numberInput.getText().toString());

            // resultado.setText("El número introducido es " + numeroIntroducidoString);
            resultado.setText("El número introducido es " + String.valueOf(numeroIntroducidoInt));
        }
    }
}