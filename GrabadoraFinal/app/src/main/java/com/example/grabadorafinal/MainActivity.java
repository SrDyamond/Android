package com.example.grabadorafinal;


import java.io.File;
import java.io.IOException;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MainActivity extends Activity implements OnCompletionListener {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    TextView tv1;
    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;
    Button grabar;
    //Permisos Audio
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) this.findViewById(R.id.textView1);
        grabar = (Button) findViewById(R.id.button);


    }

    public void grabar(View v) {
        recorder = new MediaRecorder();
        recorder.reset();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(Environment.getExternalStorageDirectory()
                .getPath());
        try {
            archivo = File.createTempFile("temporal", ".3gp", path);
        } catch (IOException e) {
        }
        recorder.setOutputFile(archivo.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
        }
        recorder.start();
        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                grabar.setEnabled(false);
                tv1.setText("Segundos restantes: " + millisUntilFinished / 1000);
                grabar.setText("Grabando");


            }

            public void onFinish() {
                detener();
                tv1.setText("Reproduciendo");
                grabar.setText("Espera");
                player.start();




            }
        }.start();



    }

    public void detener() {
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
        }
        try {
            player.prepare();
        } catch (IOException e) {
        }

    }


    public void onCompletion(MediaPlayer mp) {

        grabar.setEnabled(true);
        grabar.setText("Grabar");
        tv1.setText("");
    }
}
