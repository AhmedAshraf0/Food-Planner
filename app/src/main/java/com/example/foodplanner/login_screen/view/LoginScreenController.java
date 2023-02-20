package com.example.foodplanner.login_screen.view;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.helper.CheckConnection;
import com.example.foodplanner.login_screen.presenter.SignInInterface;
import com.example.foodplanner.login_screen.presenter.SignInPresenter;
import com.example.foodplanner.main_activity.view.MainActivity;
import com.example.foodplanner.sign_up.view.SignUp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginScreenController extends AppCompatActivity implements SignInInterface {
    private TextInputEditText et_email;
    private TextInputEditText et_password;
    private Button btnSignIn,btnGoogleSignIn;
    private FirebaseAuth mAuth;
    private CheckConnection checkConnection;
    private ProgressBar progressBar;
    private TextView tv_signUp;
    private String email,password;
    private Intent intent;
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient googleSignInClient;
    private SignInPresenter signInPresenter ;
    TextView tv_anonymously;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_controller);
        pref = getSharedPreferences("SigningInformation",MODE_PRIVATE);
        editor = pref.edit();
        if (pref.getBoolean("isSignedIn",false)){
            intent = new Intent(LoginScreenController.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        setStartSettings();
        setTv_signUpAction();
        setBtnSignInAction();
        setBtnGoogleSignInAction();
        setTv_anonymouslyAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pref.getBoolean("isSignedIn",false)){
            intent = new Intent(LoginScreenController.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
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
        btnGoogleSignIn = findViewById(R.id.btn_googleSignIn);
        et_email = findViewById(R.id.et_email_signIn);
        et_password = findViewById(R.id.et_signUpPassword);
        progressBar = findViewById(R.id.progressBarSignUp);
        tv_anonymously = findViewById(R.id.tv_anonymously);
        checkConnection = CheckConnection.getInstance(LoginScreenController.this);
        mAuth = FirebaseAuth.getInstance();
        signInPresenter = new SignInPresenter(this,LoginScreenController.this);
    }
    public void setBtnSignInAction() {
        btnSignIn = findViewById(R.id.btn_signIn_login);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                email = String.valueOf(et_email.getText());
                password = String.valueOf(et_password.getText());
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
                        signInPresenter.signIn(email,password);
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
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkConnection.isConnected()) {
                    signInPresenter.signInGoogle();
                }else{
                    Toast.makeText(LoginScreenController.this, "Turn internet on to be able to register.", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN&&resultCode == Activity.RESULT_OK&&data!=null){
            signInPresenter.respondToActivityResultOfGoogleSignIn(requestCode, resultCode, data);
        }else{
            Toast.makeText(LoginScreenController.this, "problem in the intent", Toast.LENGTH_SHORT).show();
        }
    }
    private void setTv_anonymouslyAction(){
        tv_anonymously.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isSignedIn",true);
                editor.commit();
                intent = new Intent(LoginScreenController.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onCompleteSignInWithEmailAndPassword(Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            editor.putBoolean("isSignedIn",true);
                            editor.commit();
                            intent = new Intent(LoginScreenController.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Exception exception = task.getException();
                            if (exception == null) {
                                Toast.makeText(LoginScreenController.this, "UnExpected error occurred", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginScreenController.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
    }

    @Override
    public void onCompleteGoogleSignIn(Task<AuthResult> task) {
        task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("TAG", "onSuccess: logged in");
                editor.putBoolean("isSignedIn",true);
                editor.commit();
                finish();
                intent = new Intent(LoginScreenController.this, MainActivity.class);
                startActivity(intent);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.d("TAG", "onFailure: fail to access");
                        if (exception == null) {
                            Toast.makeText(LoginScreenController.this, "UnExpected error occurred", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginScreenController.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }
    @Override
    public void onCompleteSignInIntent(Intent signInIntent, int i) {
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}



