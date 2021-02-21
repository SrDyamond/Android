package com.example.flappybirds;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import android.os.Handler;


public class GameView extends View {
    private Personaje personaje;
    private android.os.Handler handler;
    private Runnable r;
    private ArrayList<Tuberia> arrTuberias;
    private int sumpipe, distance = 500* Limites.SCREEN_HEIGHT/1920;
    private int score,bestscore=0;
    private boolean start;
    private Context context;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context= context;
        SharedPreferences sp = context.getSharedPreferences("gamestting", Context.MODE_PRIVATE);
        if(sp==null){
            bestscore= sp.getInt("bestscore",0);
        }
        score=0;
        start=false;
        initBird();
        initPipe();
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }
    private void initPipe(){
        sumpipe = 6;
        arrTuberias =new ArrayList<>();
        for (int i = 0; i < sumpipe; i++){
            if(i<sumpipe/2){
                this.arrTuberias.add(new Tuberia(Limites.SCREEN_WIDTH +i* ((Limites.SCREEN_WIDTH + 200 * Limites.SCREEN_WIDTH / 1080 )/(sumpipe / 2)),
                  0, 200* Limites.SCREEN_WIDTH /1080,   Limites.SCREEN_HEIGHT/2));
                this.arrTuberias.get(this.arrTuberias.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(),R.drawable.pipe2));
                this.arrTuberias.get(this.arrTuberias.size()-1).randomY();
            }else{
                this.arrTuberias.add(new Tuberia(this.arrTuberias.get(i-sumpipe/2).getX(), this.arrTuberias.get(i-sumpipe/2).getY()
                    +this.arrTuberias.get(i-sumpipe/2).getHeight() + this.distance, 200* Limites.SCREEN_WIDTH /1080, Limites.SCREEN_HEIGHT/2));
                        this.arrTuberias.get(this.arrTuberias.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipe1));
            }
        }
    }
    private void initBird(){
        personaje =new Personaje();
        personaje.setWidth(100* Limites.SCREEN_WIDTH /1080);
        personaje.setHeight(100* Limites.SCREEN_HEIGHT /1920);
        personaje.setX(100* Limites.SCREEN_WIDTH /1080);
        personaje.setY(700* Limites.SCREEN_HEIGHT /1920);
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(),R.drawable.gokussgod));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(),R.drawable.gokussgod));
        personaje.setArrBms(arrBms);
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(start){
            personaje.draw(canvas);
            for(int i =0; i <sumpipe; i++) {
                if(personaje.getRect().intersect(arrTuberias.get(i).getRect()) || personaje.getY()- personaje.getHeight()<0 || personaje.getY()> Limites.SCREEN_HEIGHT ){
                    Tuberia.speed=0;
                    MainActivity.txt_score_over.setText(MainActivity.txt_score.getText());
                    MainActivity.txt_best_score.setText("Best : "+bestscore);
                    MainActivity.txt_score.setVisibility(INVISIBLE);
                    MainActivity.rl_game_over.setVisibility(VISIBLE);
                }
                if(this.personaje.getX()+this.personaje.getWidth() > arrTuberias.get(i).getX()+ arrTuberias.get(i).getWidth()/2
                        && this.personaje.getX()+this.personaje.getWidth() <= arrTuberias.get(i).getX()+ arrTuberias.get(i).getWidth()/2+ Tuberia.speed
                        &&i<sumpipe/2){
                    score++;
                    if(score>bestscore){
                        bestscore=score;
                        SharedPreferences sp = context.getSharedPreferences("gamesetting",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor =sp.edit();
                        editor.putInt("bestscore",bestscore);
                        editor.apply();
                    }
                    MainActivity.txt_score.setText(""+score);
                }
                if (this.arrTuberias.get(i).getX() < -arrTuberias.get(i).getWidth()){
                    this.arrTuberias.get(i).setX(Limites.SCREEN_WIDTH);
                    if (i < sumpipe / 2) {
                        arrTuberias.get(i).randomY();
                    } else {
                        arrTuberias.get(i).setY(this.arrTuberias.get(i - sumpipe / 2).getY()
                                + this.arrTuberias.get(i - sumpipe / 2).getHeight() + this.distance);
                    }
                }
                this.arrTuberias.get(i).draw(canvas);
            }
        }else{
            if (personaje.getY()> Limites.SCREEN_HEIGHT/2) {
                personaje.setDrop(-15* Limites.SCREEN_HEIGHT/1920);
            }
            personaje.draw(canvas);
        }
        handler.postDelayed(r,10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            personaje.setDrop(-15);
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.jump_02);
            mediaPlayer.start();
            distance=distance-50;
        }
        return true;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
        distance = 500* Limites.SCREEN_HEIGHT/1920;

    }

    public void reset() {
        MainActivity.txt_score.setText("0");
        score=0;
        initPipe();
        initBird();
    }
}
