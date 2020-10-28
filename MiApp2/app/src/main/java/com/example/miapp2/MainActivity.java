package com.example.miapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;
    private EditText num3;
    private TextView resul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText)findViewById(R.id.num_1);
        num2 = (EditText)findViewById(R.id.num_2);
        num3 = (EditText)findViewById(R.id.num_3);
        resul = (TextView)findViewById(R.id.resultado);
    }

    public void calcular(View view){
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        String valor3=num3.getText().toString();
        int num1=Integer.parseInt(valor1);
        int num2=Integer.parseInt(valor2);
        int num3=Integer.parseInt(valor3);

        int media =(num1+num2+num3)/3;
        String resu= String.valueOf(media);

        if (media >=5){
            resul.setText("Aprobado con media de :"+resu);
        } else{
            resul.setText("Suspenso con media de :"+resu);
        }
    }


}