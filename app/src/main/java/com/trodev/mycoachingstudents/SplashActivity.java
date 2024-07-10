package com.trodev.mycoachingstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.trodev.mycoachingstudents.auth_activity.SigninActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*hide actionbar*/
        getSupportActionBar().hide();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isUserSigninauto();
            }
        }, 4000);
    }

    private void isUserSigninauto() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            Toast.makeText(this, "SignIn Successful", Toast.LENGTH_SHORT).show();
            // off_onboarding();
            finishAffinity();
        } else {
            startActivity(new Intent(SplashActivity.this, SigninActivity.class));
        }
    }

}