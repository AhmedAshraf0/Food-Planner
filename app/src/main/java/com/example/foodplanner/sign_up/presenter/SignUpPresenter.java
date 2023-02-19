package com.example.foodplanner.sign_up.presenter;
import android.content.Context;

import com.example.foodplanner.firebaseRepository.FirebaseRepository;
public class SignUpPresenter {
    private SignUpInterface signUpInterface;
    private FirebaseRepository firebaseRepository;
    private Context context;
    public SignUpPresenter(SignUpInterface signUpInterface){
        this.signUpInterface = signUpInterface;
    }
    public void createUserWithEmailAndPassword(String email, String password) {
        firebaseRepository = new FirebaseRepository(signUpInterface);
        firebaseRepository.createUserWithEmailAndPassword(email, password);
    }
}
