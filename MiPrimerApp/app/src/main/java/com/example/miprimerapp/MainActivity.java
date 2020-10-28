package com.example.miprimerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recuperamos los datos introducidos
        num1 = (EditText)findViewById(R.id.num_1);
        num2 = (EditText)findViewById(R.id.num_2);
        resultado = (TextView)findViewById(R.id.resultado);
        }

    //metodo suma
    public void Sumar(View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        int num1 = Integer.parseInt(valor1);
        int num2 = Integer.parseInt(valor2);
        int suma =num1 +num2;

        String resu= String.valueOf(suma);
        resultado.setText(resu);
    }
}