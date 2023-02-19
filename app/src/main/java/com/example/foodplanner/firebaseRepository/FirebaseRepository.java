package com.example.foodplanner.firebaseRepository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner.R;
import com.example.foodplanner.login_screen.presenter.SignInInterface;
import com.example.foodplanner.login_screen.view.LoginScreenController;
import com.example.foodplanner.sign_up.presenter.SignUpInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class FirebaseRepository {
    private SignUpInterface signUpInterface;
    private SignInInterface signInInterface;
    private Context context;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public FirebaseRepository(){}
    public FirebaseRepository(SignUpInterface SignUpInterface) {
        this.signUpInterface = SignUpInterface;
    }
    public FirebaseRepository(SignInInterface signInInterface,Context context) {
        this.signInInterface = signInInterface;
        this.context = context;
    }
    public void createUserWithEmailAndPassword(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signUpInterface.onCompleteRegisterWithEmailAndPassword(task);
            }
        }) ;
    }
    public void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signInInterface.onCompleteSignInWithEmailAndPassword(task);
            }
        }) ;
    }
    public void signInGoogle() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("463576206412-lgqul6laioo7j919dis2u9efqj9128ce.apps.googleusercontent.com")
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context,googleSignInOptions);
        Intent googleIntent = googleSignInClient.getSignInIntent();
        signInInterface.onCompleteSignInIntent(googleIntent, 100);
    }
    public void respondToActivityResultOfGoogleSignIn(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            firebaseAuth.signInWithCredential(GoogleAuthProvider.getCredential(accountTask.getResult().getIdToken(), null))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            signInInterface.onCompleteGoogleSignIn(task);
                        }
                    }) ;
        }
    }
}
