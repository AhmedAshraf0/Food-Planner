package com.example.foodplanner.login_screen.presenter;

import android.content.Intent;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface SignInInterface {
    void onCompleteSignInWithEmailAndPassword(Task<AuthResult> task);

    void onCompleteGoogleSignIn(Task<AuthResult> task);

    void onCompleteSignInIntent(Intent signInIntent, int i);
}
