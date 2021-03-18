package com.outil.Horloge.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.outil.Horloge.MainActivity;
import com.outil.Horloge.R;
import com.outil.Horloge.base.BaseActivity;

public class Chronometre extends BaseActivity {

    LinearLayout Zone_config;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometre);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat(" %s");
        chronometer.setBase(SystemClock.elapsedRealtime());


    }
    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }


    public void Chrono(View view) {
        GotoChronometre();
    }



    public void CountDown(View view) {
        GotoCountdown();
    }
}