package com.example.foodplanner.landingPage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.login_screen.view.LoginScreenController;
import com.example.foodplanner.main_activity.view.MainActivity;
import com.example.foodplanner.sign_up.view.SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LandingPage extends AppCompatActivity {
    Button sign_up;
    TextView sign_in;
    Intent intent;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        pref = getSharedPreferences("AppearingInfo",MODE_PRIVATE);
        editor = pref.edit();
        if (pref.getBoolean("isAppeared",false)){
            intent = new Intent(LandingPage.this, LoginScreenController.class);
            startActivity(intent);
            Log.i("isAppeared", "IS APPEARED TRUE");
            finish();
        }{
            Log.i("isAppeared", "IS APPEARED FALSE");
        }
        setSign_upBtnAction();
        setSignInTextViewAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pref.getBoolean("isAppeared",false)){
            intent = new Intent(LandingPage.this, LoginScreenController.class);
            startActivity(intent);
            Log.i("isAppeared", "IS APPEARED TRUE");
            finish();
        }{
            Log.i("isAppeared", "IS APPEARED FALSE");
        }
    }

    public void setSign_upBtnAction() {
        sign_up = findViewById(R.id.btn_signUp);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isAppeared",true);
                editor.commit();
                intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
    public void setSignInTextViewAction(){
        sign_in = findViewById(R.id.tv_signInLandingPage);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isAppeared",true);
                editor.commit();
                intent = new Intent(getApplicationContext(), LoginScreenController.class);
                startActivity(intent);
            }
        });
    }
}