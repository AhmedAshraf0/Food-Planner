package com.example.foodplanner.firebaseRepository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.foodplanner.sign_up.presenter.SignUpInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseRepository {
    private SignUpInterface SignUpInterface;
    private Context context;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public FirebaseRepository(){}
    public FirebaseRepository(SignUpInterface SignUpInterface) {
        this.SignUpInterface = SignUpInterface;
    }
    public void createUserWithEmailAndPassword(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                SignUpInterface.onCompleteRegisterWithEmailAndPassword(task);
            }
        }) ;
    }

}
