package benforsrup.mvptest.ui.login.presenter;

/**
 * Created by benforsrup on 2017-07-25.
 */

public interface FirebaseLoginPresenter {

    void registerListener();
    void unregisterListener();
    void initilizeAuth();
    void receiveUserLogin(String email, String password);
    void onFailure();
    void onSuccess(String user, String uid);

}