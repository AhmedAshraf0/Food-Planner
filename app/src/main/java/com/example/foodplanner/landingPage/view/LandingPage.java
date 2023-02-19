package com.example.foodplanner.landingPage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private FirebaseAuth mAuth;
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            intent = new Intent(LandingPage.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        mAuth = FirebaseAuth.getInstance();
        setSign_upBtnAction();
        setSignInTextViewAction();
    }

    public void setSign_upBtnAction() {
        sign_up = findViewById(R.id.btn_signUp);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void setSignInTextViewAction(){
        sign_in = findViewById(R.id.tv_signInLandingPage);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), LoginScreenController.class);
                startActivity(intent);
            }
        });
    }
}