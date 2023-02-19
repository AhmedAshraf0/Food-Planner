package com.example.foodplanner.login_screen.presenter;
import android.content.Context;
import android.content.Intent;
import com.example.foodplanner.firebaseRepository.FirebaseRepository;

public class SignInPresenter {
    private SignInInterface signInInterface;
    private Context context;
    private FirebaseRepository firebaseRepository;
    public SignInPresenter(SignInInterface signInInterface, Context context){
        this.signInInterface = signInInterface;
        this.context = context;
    }
    public void signIn(String email, String password) {
        firebaseRepository = new FirebaseRepository(signInInterface, context);
        firebaseRepository.signIn(email, password);
    }
    public void signInGoogle() {
        firebaseRepository = new FirebaseRepository(signInInterface, context);
        firebaseRepository.signInGoogle();
    }
    public void respondToActivityResultOfGoogleSignIn(int requestCode, int resultCode, Intent data) {
        firebaseRepository = new FirebaseRepository(signInInterface, context);
        firebaseRepository.respondToActivityResultOfGoogleSignIn(requestCode, resultCode, data);
    }


}
