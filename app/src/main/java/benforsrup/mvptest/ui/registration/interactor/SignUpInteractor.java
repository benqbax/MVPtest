package benforsrup.mvptest.ui.registration.interactor;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import benforsrup.mvptest.ui.registration.presenter.SignUpPresenter;

/**
 * Created by benforsrup on 2017-07-26.
 */

public class SignUpInteractor implements SInteractor {

    private SignUpPresenter mSignUpPresenter;

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public SignUpInteractor(SignUpPresenter presenter) {
        this.mSignUpPresenter = presenter;
    }

    @Override
    public void createNewUser(String username, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        mSignUpPresenter.userCreated(user);
                    }
                });
    }
}
