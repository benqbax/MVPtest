package benforsrup.mvptest.ui.registration.interactor;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import benforsrup.mvptest.model.User;
import benforsrup.mvptest.ui.registration.presenter.SignUpPresenter;

/**
 * Created by benforsrup on 2017-07-26.
 */

public class SignUpInteractor implements SInteractor {
    private static final String TAG = "SignUpInteractor";
    private SignUpPresenter mSignUpPresenter;

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public SignUpInteractor(SignUpPresenter presenter) {
        this.mSignUpPresenter = presenter;
    }

    @Override
    public void createNewUser(String username, String email, String password) {
        final User databaseUser = new User();
        databaseUser.setEmail(email);
        databaseUser.setUsername(username);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();


                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference();
                        myRef.child("Users").child(user.getUid()).setValue(databaseUser);

                        mSignUpPresenter.userCreated(user);
                    }
                });
    }
}
