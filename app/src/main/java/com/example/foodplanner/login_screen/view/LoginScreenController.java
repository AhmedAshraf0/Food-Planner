package com.example.foodplanner.login_screen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.main_activity.view.MainActivity;
import com.example.foodplanner.sign_up.view.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreenController extends AppCompatActivity {
    private TextInputEditText et_email;
    private TextInputEditText et_password;
    private Button btnSignIn,btnGoogleSignIn;
    private FirebaseAuth mAuth;
    private CheckConnection checkConnection;
    private ProgressBar progressBar;
    private TextView tv_signUp;
    private String email;
    Intent intent;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            intent = new Intent(LoginScreenController.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_controller);
        setStartSettings();
        setTv_signUpAction();
        setBtnSignInAction();
    }
    void setTv_signUpAction(){
       tv_signUp = findViewById(R.id.tv_signUp);
       tv_signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(LoginScreenController.this, SignUp.class);
               startActivity(intent);
               finish();
           }
       });
    }
    void setStartSettings(){
        et_email = findViewById(R.id.et_email_signIn);
        et_password = findViewById(R.id.et_signUpPassword);
        progressBar = findViewById(R.id.progressBarSignUp);
        checkConnection = CheckConnection.getInstance(LoginScreenController.this);
        mAuth = FirebaseAuth.getInstance();
    }
    public void setBtnSignInAction() {
        btnSignIn = findViewById(R.id.btn_signIn_login);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                email = String.valueOf(et_email.getText());
                String password = String.valueOf(et_password.getText());
                if (checkConnection.isConnected()) {
                    if (email.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginScreenController.this, "Enter your email", Toast.LENGTH_SHORT).show();
                    }
                    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginScreenController.this, "The email's format is not valid", Toast.LENGTH_SHORT).show();
                    }
                    else if (password.isEmpty()) {
                        Toast.makeText(LoginScreenController.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            intent = new Intent(LoginScreenController.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            Exception exception = task.getException();
                                            if (exception == null) {
                                                Toast.makeText(LoginScreenController.this, "UnExpected error occurred", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (exception.getClass().equals(FirebaseAuthException.class)) {
                                                    if (((FirebaseAuthException) exception).getErrorCode().equals("ERROR_USER_NOT_FOUND")) {
                                                        Toast.makeText(LoginScreenController.this, "User not found", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(LoginScreenController.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                } else if (exception.getClass().equals(FirebaseNetworkException.class)) {
                                                    Toast.makeText(LoginScreenController.this, "Network error", Toast.LENGTH_SHORT).show();
                                                }
                                                else if (task.getException().getMessage().equals("There is no user record corresponding to this identifier. The user may have been deleted.")) {
                                                    Toast.makeText(LoginScreenController.this, "User not found", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    Toast.makeText(LoginScreenController.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        }
                                    }
                                });
                    }
                }
                else {
                    Toast.makeText(LoginScreenController.this, "Turn internet on to be able to register.", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    };
    public void setBtnGoogleSignInAction(){

    }
}



