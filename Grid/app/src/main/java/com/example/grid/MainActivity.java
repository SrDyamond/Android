package com.example.grid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Iniciando", Toast.LENGTH_SHORT).show();
       // this.añadeHijos();
        Toast.makeText(this, "pipipi", Toast.LENGTH_SHORT).show();

    }


    public void añadeHijos(){
        GridLayout g =findViewById(R.id.grid);
        Button b;
        Toast.makeText(this, "Creando grid...", Toast.LENGTH_SHORT).show();
        for (int i=0;i>5;i++){
            b=new Button(this);
            b.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            b.setText("btn"+1);
            b.setTag("boton"+1);
            g.addView(b,i);

        }
    }
}