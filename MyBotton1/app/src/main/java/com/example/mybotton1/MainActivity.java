package com.example.mybotton1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //referencias a los widgets añadidos
    Button miBoton;
    TextView miTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miBoton = (Button) findViewById(R.id.button);
        miBoton.setOnClickListener(this);
    }
        public void onClick(View view) {
        //responde al evento Click
        miTexto=(TextView) findViewById(R.id.textView);
        miTexto.setText("pulsado");
    }
}