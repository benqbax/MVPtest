package benforsrup.mvptest.ui.main.interactor;

/**
 * Created by benforsrup on 2017-07-25.
 */


public interface MInteractor {
    void checkIfUserHasLoggedIn();
    void register();
    void unregister();
    void initilizeAuth();
    void signOut();
}