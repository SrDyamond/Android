package com.example.miapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText num1,num2;
    private TextView res;
    private RadioButton rb_su,rb_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1=(EditText)findViewById(R.id.num_1);
        num2=(EditText)findViewById(R.id.num_2);
        res=(TextView)findViewById(R.id.resultado);
        rb_su=(RadioButton)findViewById(R.id.rb_sumar);
        rb_re=(RadioButton)findViewById(R.id.rb_restar);
    }

    public void calcular(View view){
        String valor_1=num1.getText().toString();
        String valor_2=num2.getText().toString();
        String resultado="";
        int num1=Integer.parseInt(valor_1);
        int num2=Integer.parseInt(valor_2);
        int resta=0;

        if(rb_su.isChecked() == true){
            int suma=num1+num2;
            resultado=String.valueOf(suma);
            res.setText("La suma es :"+resultado);
        }else if(rb_re.isChecked() == true){
            if (num1<num2) {
                resta = num2 - num1;
            }else if (num1>num2) {
                resta=num1 -num2;
            }
            resultado=String.valueOf(resta);
            res.setText("La resta es :"+resultado);
        }
    }
}