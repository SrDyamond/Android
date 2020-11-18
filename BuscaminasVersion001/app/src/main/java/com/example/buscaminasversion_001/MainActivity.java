package com.example.buscaminasversion_001;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton gif;
    private RadioButton rb_easy,rb_medium,rb_hard;
    private TextView dificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb_easy=(RadioButton)findViewById(R.id.rb_1);
        rb_medium=(RadioButton)findViewById(R.id.rb_2);
        rb_hard=(RadioButton)findViewById(R.id.rb_3);
        gif=(ImageButton) findViewById(R.id.imageButton);
        gif.setBackgroundResource(R.drawable.loading);
        AnimationDrawable frameAnimation=(AnimationDrawable) gif.getBackground();
        frameAnimation.start();
    }


    public void jugar(View v) {

        Intent intent = new Intent (v.getContext(), MainActivityJuego.class);
        //Exportamos array a ActivityJuego

        if(rb_easy.isChecked()){
            int[]tablero = {9,9,10};
            intent.putExtra("Array", tablero);
        }
        if(rb_medium.isChecked()){
            int[] tablero = {16,16,40 };
            intent.putExtra("Array", tablero);
        }
        if(rb_hard.isChecked()){
            int[]tablero = {20, 20, 70};
            intent.putExtra("Array", tablero);
        }
        startActivityForResult(intent, 0);
    }
}