package com.example.buscaminasversion_001;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton rb_easy,rb_medium,rb_hard;
    private TextView dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb_easy=(RadioButton)findViewById(R.id.rb_1);
        rb_medium=(RadioButton)findViewById(R.id.rb_2);
        rb_hard=(RadioButton)findViewById(R.id.rb_3);
        dificultad=(TextView)findViewById(R.id.dificultad);
        dificultad.setText("Por defecto : Facil");
    }

    public void dificultad(View view) {
        String dif="";
        if (rb_easy.isChecked() == true) {
            dif = "Facil";
            dificultad.setText("La dificultad  es : " + dif);
        }
        if (rb_medium.isChecked() == true) {
            dif = "Medio";
            dificultad.setText("La dificultad  es : " + dif);
        }
        if (rb_hard.isChecked() == true) {
            dif = "Dificil";
            dificultad.setText("La dificultad  es : " + dif);
        }
    }
    public void jugar(View v) {
        Toast.makeText(this, dificultad.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (v.getContext(), MainActivityJuego.class);
        //Exportamos array a ActivityJuego

        if(dificultad.getText().equals("La dificultad  es : Facil" )){
            int[]tablero = {9,9,10};
            intent.putExtra("Array", tablero);
        }
        if(dificultad.getText().equals("La dificultad  es : Medio" )){
            int[] tablero = {16,16,40 };
            intent.putExtra("Array", tablero);
        }
        if(dificultad.getText().equals("La dificultad  es : Dificil" )){
            int[]tablero = {16, 21, 85};
            intent.putExtra("Array", tablero);
        }
        startActivityForResult(intent, 0);
    }
}