package benforsrup.mvptest.ui.registration.presenter;

import com.google.firebase.auth.FirebaseUser;

import benforsrup.mvptest.ui.registration.interactor.SignUpInteractor;
import benforsrup.mvptest.ui.registration.view.SignUpView;

/**
 * Created by benforsrup on 2017-07-26.
 */

public class SignUpPresenterImpl implements SignUpPresenter {
    private static final String TAG = "SignUpPresenterImpl";

    private final SignUpView mSignUpView;
    private  final SignUpInteractor mSignUpInteractor;

    public SignUpPresenterImpl(SignUpView view) {
        this.mSignUpView = view;
        mSignUpInteractor = new SignUpInteractor(this);
    }

    @Override
    public void attemptToCreateUser(String username, String email, String password) {
        mSignUpInteractor.createNewUser(username, email, password);
    }

    @Override
    public void userCreated(FirebaseUser user) {
        mSignUpView.onSuccessCreatedUser();
    }
}
