package com.outil.Horloge;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.outil.Horloge.Interface.Chronometre;
import com.outil.Horloge.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
    Button Entrer;
    Button Connect;
    Button Deconnect;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 123;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Entrer = findViewById(R.id.entrer);
        Connect = findViewById(R.id.connect);
        Deconnect = findViewById(R.id.deconnect);
        mAuth = FirebaseAuth.getInstance();
}
    private void startSignInActivity(){
    // Choose authentication providers
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());
     // Create and launch sign-in intent
    startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                    .setTheme(R.style.LoginTheme)
                .setIsSmartLockEnabled(false, true)
                .setLogo(R.drawable.logo)
                .build(),
    RC_SIGN_IN);}


    public void connecter(View view) {
        this.startSignInActivity();
    }

    public void deconnecter(View view) {
        constraintLayout = findViewById(R.id.main_activity_constraint_layout);
        if (this.getCurrentUser()!=null){
            AuthUI.getInstance()
                .signOut(this)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {


                    }
                });
        }else{
            showSnackBar(constraintLayout,getString(R.string.tu_dois_connecter));
        }
    }
    private void showSnackBar(ConstraintLayout constraintLayout, String message){
        Snackbar.make(constraintLayout, message, Snackbar.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        constraintLayout = findViewById(R.id.main_activity_constraint_layout);
        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS

                showSnackBar(this.constraintLayout, getString(R.string.connection_succeed));

            } else { // ERRORS
                if (response == null) {
                    showSnackBar(this.constraintLayout, getString(R.string.error_authentication_canceled));
                } else if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackBar(this.constraintLayout, getString(R.string.error_no_internet));
                } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackBar(this.constraintLayout, getString(R.string.error_unknown_error));
                }
            }
        }
    }
    private void updateUIWhenResuming(){
        Connect =findViewById(R.id.connect);
        if (this.isCurrentUserLogged()){
            Connect.setText(getString(R.string.button_login_text_logged));
        }else{
            Connect.setText(getString(R.string.button_login_text_not_logged));
        }

    }

    public void Entrer(View view) {
        constraintLayout = findViewById(R.id.main_activity_constraint_layout);
        if (this.isCurrentUserLogged()){
        Intent go = new Intent();
        go.setClass(MainActivity.this, Chronometre.class);
        startActivity(go);}
        else{
            showSnackBar(this.constraintLayout, getString(R.string.tu_dois_connecter));
        }
    }
@Override
    public void onResume() {
        super.onResume();
    this.updateUIWhenResuming();

}

}