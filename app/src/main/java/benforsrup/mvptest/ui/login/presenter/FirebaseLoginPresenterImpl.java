package benforsrup.mvptest.ui.login.presenter;

import benforsrup.mvptest.ui.login.interactor.LoginInteractor;
import benforsrup.mvptest.ui.login.view.LoginView;
import benforsrup.mvptest.ui.login.view.SignUpView;

/**
 * Created by benforsrup on 2017-07-25.
 */

public class FirebaseLoginPresenterImpl implements FirebaseLoginPresenter {

    private final LoginView mLoginView;
    private final SignUpView mSignUpView;
    private final LoginInteractor mInteractor;

    public FirebaseLoginPresenterImpl(LoginView view, SignUpView signUpView) {
        mInteractor = new LoginInteractor(this);
        this.mSignUpView = signUpView;
        this.mLoginView = view;


    }



    @Override
    public void registerListener() {
        mInteractor.register();
    }

    @Override
    public void unregisterListener() {
        mInteractor.unregister();
    }

    @Override
    public void initilizeAuth() {
        mInteractor.initilizeAuth();
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
    public void onSuccess(String user, String uid) {
        mLoginView.logTheUserIn(user, uid);
    }

    @Override
    public void attemptToCreateUser(String email, String password) {
        mInteractor.createUser(email, password);
    }

    @Override
    public void onCreateUserSuccess() {
        mSignUpView.onSuccessCreatedUser();
    }


}
