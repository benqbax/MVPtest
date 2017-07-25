package benforsrup.mvptest.ui.main.presenter;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by benforsrup on 2017-07-25.
 */

public interface MainPresenter {
    void checkIfUserHasLoggedIn();
    void onLoggedIn(FirebaseUser user);
    void onSignedOut();
    void registerListener();
    void unregisterListener();
    void initilizeAuth();
    void signUserOut();
}
