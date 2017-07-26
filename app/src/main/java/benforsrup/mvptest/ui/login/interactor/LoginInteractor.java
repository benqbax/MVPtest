package benforsrup.mvptest.ui.login.interactor;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;
import java.util.concurrent.Executor;

import benforsrup.mvptest.ui.login.presenter.FirebaseLoginPresenter;
import benforsrup.mvptest.ui.login.view.LoginActivity;

/**
 * Created by benforsrup on 2017-07-25.
 */

public class LoginInteractor implements LInteractor {
    private static final String TAG = "LoginInteractor";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private final FirebaseLoginPresenter presenter;

    public LoginInteractor(FirebaseLoginPresenter pre) {
        this.presenter = pre;
    }


    @Override
    public void attemptToLogIn(String email, String password) {
        if(!validate(email, password)){
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: Logging in complete");
                        }
                        else {
                            Log.d(TAG, "onComplete: failed");
                            presenter.onFailure("Wrong password or email");
                        }
                    }
                });
    }


    public boolean validate(String email, String password){
        boolean valid = true;
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Log.d(TAG, "validate: Enter a valid email");
            presenter.onFailure("Enter a valid email");
            return false;
        }

        if(password.isEmpty() || password.length()<4 || password.length() >10 ){
            Log.d(TAG, "validate: Enter a valid password");
            presenter.onFailure("Enter a valid password");
            return false;
        }

        return valid;


    }


    @Override
    public void register() {
        mAuth.addAuthStateListener(mAuthStateListener);
        Log.d(TAG, "register: registered the listener");
    }

    @Override
    public void unregister() {
        if(mAuthStateListener!=null){
            mAuth.removeAuthStateListener(mAuthStateListener);
            Log.d(TAG, "unregister: unregistered the listener");
        }
    }

    @Override
    public void initilizeAuth() {
        mAuth = FirebaseAuth.getInstance();

        //auth state listener
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    presenter.onSuccess(user.getEmail(), user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        Log.d(TAG, "initilizeAuth: Initilized the authenticator " + mAuth.toString());
    }


}
