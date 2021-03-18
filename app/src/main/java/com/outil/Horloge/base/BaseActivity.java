package com.outil.Horloge.base;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.outil.Horloge.Interface.Chronometre;
import com.outil.Horloge.Interface.Countdown;
import com.outil.Horloge.Interface.WackUp;
import com.outil.Horloge.R;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }
    protected Boolean isCurrentUserLogged(){ return (this.getCurrentUser() != null); }


    public void GotoChronometre(){
        Intent gotochronometre = new Intent();
        gotochronometre.setClass(this, Chronometre.class);
        startActivity(gotochronometre);
    }
    public void GotoCountdown(){
        Intent gotochronometre = new Intent();
        gotochronometre.setClass(this, Countdown.class);
        startActivity(gotochronometre);
    }


}
