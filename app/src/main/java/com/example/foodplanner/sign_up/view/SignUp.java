package com.example.foodplanner.sign_up.view;

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
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.R;
import com.example.foodplanner.login_screen.view.LoginScreenController;
import com.example.foodplanner.main_activity.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private TextInputEditText signUp_email, signUp_password, confirmPassword;
    private TextView loginTV;
    private FirebaseAuth mAuth;
    CheckConnection checkConnection;
    private String email;
    private Button btnRegister;
    ProgressBar progressBar;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUp_UI();
        setBtnRegisterLogic();
        setSignInTextViewAction();
    }
    private void setUp_UI(){
        signUp_email = findViewById(R.id.et_email_signUp);
        signUp_password = findViewById(R.id.et_signUpPassword);
        confirmPassword = findViewById(R.id.et_confirmPassword);
        progressBar = findViewById(R.id.progressBarSignUp);
        checkConnection = CheckConnection.getInstance(SignUp.this);
        mAuth = FirebaseAuth.getInstance();
    }
    private void setBtnRegisterLogic(){
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                email = String.valueOf(signUp_email.getText());
                String password = String.valueOf(signUp_password.getText());
                String confirm = String.valueOf(confirmPassword.getText());
                if (checkConnection.isConnected()) {
                    if (email.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this, "Enter your email", Toast.LENGTH_SHORT).show();
                    } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this, "The email's format is not valid", Toast.LENGTH_SHORT).show();
                    }
                    else if (password.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    } else if (password.length() <8){
                        // If sign in fails, display a message to the user.
                        Toast.makeText(SignUp.this, "weak password,it should contains at least eight character.",
                                Toast.LENGTH_LONG).show();
                    }
                    else if (!password.equals(confirm)){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this, "Password not identical", Toast.LENGTH_SHORT).show();
                    }
                    else{

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), LoginScreenController.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Exception exception = task.getException();
                                        if (exception == null) {
                                            Toast.makeText(SignUp.this, "UnExpected error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(SignUp.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                    }
                }else{
                    Toast.makeText(SignUp.this, "Turn internet on to be able to register.", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
    private void setSignInTextViewAction(){
        loginTV = findViewById(R.id.tv_signUpRegister);
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LoginScreenController.class);
                startActivity(intent);
            }
        });
    }
}