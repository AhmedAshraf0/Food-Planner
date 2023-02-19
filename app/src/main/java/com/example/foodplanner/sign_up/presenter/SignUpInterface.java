package com.example.foodplanner.sign_up.presenter;

import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
public interface SignUpInterface {
    void onCompleteRegisterWithEmailAndPassword(Task<AuthResult> task);
}
