package com.outil.Horloge.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.outil.Horloge.R;
import com.outil.Horloge.base.BaseActivity;

public class WackUp extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveil);

    }
    public void Chrono(View view) {
        GotoChronometre();
    }



    public void CountDown(View view) {
        GotoCountdown();
    }
}