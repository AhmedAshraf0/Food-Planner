package com.example.foodplanner.splashScreen.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodplanner.R;
import com.example.foodplanner.landingPage.view.LandingPage;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
         handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LandingPage.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable, 5000);
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,5000);
    }
}