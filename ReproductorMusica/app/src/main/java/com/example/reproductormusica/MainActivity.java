package com.example.reproductormusica;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    public MediaPlayer sonido;
    public Button Bplay,Bpause,Bstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sonido = MediaPlayer.create(this,R.raw.speed);
        Bplay=(Button)findViewById(R.id.play);
        Bpause=(Button)findViewById(R.id.pause);
        Bstop=(Button)findViewById(R.id.stop);
        this.sonido =MediaPlayer.create(this, R.raw.speed);


    }
    public void play(View view){
        sonido.start();
    }

    public void  pause(View view){
        sonido.pause();
    }

    public void  stop(View view){
        sonido.stop();
        sonido = MediaPlayer.create(this,R.raw.speed);
    }

}
