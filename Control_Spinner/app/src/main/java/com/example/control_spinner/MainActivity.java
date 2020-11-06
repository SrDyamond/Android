package com.example.control_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText valor1,valor2;
    private TextView resultadopantalla;
    private Spinner spinner1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1=(EditText)findViewById(R.id.valor_1);
        valor2=(EditText)findViewById(R.id.valor_2);
        resultadopantalla=(TextView)findViewById(R.id.resultado);
        spinner1=(Spinner)findViewById(R.id.spinner);
        //Creo un array con las opciones para el spinner
        String [] opciones= {"Sumar","Restar","Multiplicar","Dividir"};
        //Uso ArrayAdapter para meter el array dentro del spinner
        //Spinner por defecto
        //ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        //XML creado por mi para cambiar el color y tamaño de las letras del spinner
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item_dyango,opciones);
        spinner1.setAdapter(adapter);
    }
    //Metodo del boton

    public void calcular(View view) {
        String valor1_string = valor1.getText().toString();
        String valor2_string = valor2.getText().toString();

        int valor1_int = Integer.parseInt(valor1_string);
        int valor2_int = Integer.parseInt(valor2_string);

        String seleccion = spinner1.getSelectedItem().toString();
        String resultado="";
        int operacion=0;
        if (seleccion.equals("Sumar")) {
            operacion = valor1_int + valor2_int;
            resultado = String.valueOf(operacion);
            resultadopantalla.setText(resultado);
        } else if (seleccion.equals("Resta")) {
            operacion = valor1_int - valor2_int;
            resultado = String.valueOf(operacion);
            resultadopantalla.setText(resultado);
        } else if (seleccion.equals("Multiplicar")) {
            operacion = valor1_int * valor2_int;
            resultado = String.valueOf(operacion);
            resultadopantalla.setText(resultado);
        } else if (seleccion.equals("Dividir")) {
            operacion = valor1_int / valor2_int;
            resultado = String.valueOf(operacion);
            resultadopantalla.setText(resultado);

        }
    }

}