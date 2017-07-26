package benforsrup.mvptest.ui.login.presenter;

import com.google.firebase.auth.FirebaseUser;

import benforsrup.mvptest.ui.login.interactor.LoginInteractor;
import benforsrup.mvptest.ui.login.view.LoginView;

/**
 * Created by benforsrup on 2017-07-25.
 */

public class FirebaseLoginPresenterImpl implements FirebaseLoginPresenter {

    private final LoginView mLoginView;
    private final LoginInteractor mInteractor;

    public FirebaseLoginPresenterImpl(LoginView view) {
        mInteractor = new LoginInteractor(this);
        this.mLoginView = view;
    }


    @Override
    public void receiveUserLogin(String email, String password) {
        mInteractor.attemptToLogIn(email, password);
    }

    @Override
    public void onFailure(String message) {
        mLoginView.onFailure(message);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        mLoginView.logTheUserIn(user);
    }

    @Override
    public void attemptToCreateUser(String email, String password) {
        mInteractor.createUser(email, password);
    }



}
