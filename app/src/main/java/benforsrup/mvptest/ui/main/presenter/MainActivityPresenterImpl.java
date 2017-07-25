package benforsrup.mvptest.ui.main.presenter;

import com.google.firebase.auth.FirebaseUser;

import benforsrup.mvptest.ui.main.interactor.MainInteractor;
import benforsrup.mvptest.ui.main.view.MainView;

/**
 * Created by benforsrup on 2017-07-25.
 */

public class MainActivityPresenterImpl implements MainPresenter {
    private final MainView mainView;
    private final MainInteractor interactor;

    public MainActivityPresenterImpl(MainView view) {
        this.mainView = view;
        interactor = new MainInteractor(this);
    }


    @Override
    public void checkIfUserHasLoggedIn() {
        interactor.checkIfUserHasLoggedIn();
    }

    @Override
    public void onLoggedIn(FirebaseUser user) {
        mainView.logtheUserIn(user);
    }

    @Override
    public void onSignedOut() {
        mainView.onSignedOut();
    }

    @Override
    public void registerListener() {
        interactor.register();
    }

    @Override
    public void unregisterListener() {
        interactor.unregister();
    }

    @Override
    public void initilizeAuth() {
        interactor.initilizeAuth();
    }

    @Override
    public void signUserOut() {
        interactor.signOut();
    }


}
