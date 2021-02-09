package com.example.flappybirds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import android.os.Handler;


public class GameView extends View {
    private Bird bird;
    private android.os.Handler handler;
    private Runnable r;
    private ArrayList<Pipe> arrPipes;
    private int sumpipe, distance;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
        distance = 300*Constants.SCREEN_HEIGHT/1920;
        arrPipes =new ArrayList<>();
        for (int i = 0; i < sumpipe; i++){
            if(i<sumpipe/2){
                this.arrPipes.add(new Pipe(Constants.SCREEN_WIDTH +i* ((Constants.SCREEN_WIDTH + 200 * Constants.SCREEN_WIDTH / 1080 )/(sumpipe / 2)),
                  0, 200*Constants.SCREEN_WIDTH /1080,   Constants.SCREEN_HEIGHT/2));
                this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(),R.drawable.pipe2));
                this.arrPipes.get(this.arrPipes.size()-1).randomY();
            }else{
                this.arrPipes.add(new Pipe(this.arrPipes.get(i-sumpipe/2).getX(), this.arrPipes.get(i-sumpipe/2).getY()
                    +this.arrPipes.get(i-sumpipe/2).getHeight() + this.distance, 200*Constants.SCREEN_WIDTH /1080, Constants.SCREEN_HEIGHT/2));
                        this.arrPipes.get(this.arrPipes.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pipe1));
            }
        }
    }
    private void initBird(){
        bird=new Bird();
        bird.setWidth(100*Constants.SCREEN_WIDTH /1080);
        bird.setHeight(100*Constants.SCREEN_HEIGHT /1920);
        bird.setX(100*Constants.SCREEN_WIDTH /1080);
        bird.setY(700*Constants.SCREEN_HEIGHT /1920);
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(),R.drawable.bird1));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(),R.drawable.bird2));
        bird.setArrBms(arrBms);
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
        bird.draw(canvas);
        for(int i =0; i <sumpipe; i++) {
            if (this.arrPipes.get(i).getX() < -arrPipes.get(i).getWidth()){
                this.arrPipes.get(i).setX(Constants.SCREEN_WIDTH);
                if (i < sumpipe / 2) {
                    arrPipes.get(i).randomY();
                } else {
                    arrPipes.get(i).setY(this.arrPipes.get(i - sumpipe / 2).getY()
                            + this.arrPipes.get(i - sumpipe / 2).getHeight() + this.distance);
                }
            }
            this.arrPipes.get(i).draw(canvas);
        }
        handler.postDelayed(r,10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            bird.setDrop(-15);
        }
        return true;
    }
}
