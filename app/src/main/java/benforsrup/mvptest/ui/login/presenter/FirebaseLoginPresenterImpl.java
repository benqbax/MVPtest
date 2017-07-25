package benforsrup.mvptest.ui.login.presenter;

import benforsrup.mvptest.ui.login.interactor.LoginInteractor;
import benforsrup.mvptest.ui.login.view.LoginView;

/**
 * Created by benforsrup on 2017-07-25.
 */

public class FirebaseLoginPresenterImpl implements FirebaseLoginPresenter {

    private final LoginView mLoginView;
    private final LoginInteractor mInteractor;

    public FirebaseLoginPresenterImpl(LoginView view) {
        this.mLoginView = view;
        mInteractor = new LoginInteractor(this);
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
    public void onFailure() {
        mLoginView.onFailure();
    }

    @Override
    public void onSuccess(String user, String uid) {
        mLoginView.logTheUserIn(user, uid);
    }
}
