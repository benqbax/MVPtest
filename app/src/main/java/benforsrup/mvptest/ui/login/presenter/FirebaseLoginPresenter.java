package benforsrup.mvptest.ui.login.presenter;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by benforsrup on 2017-07-25.
 */

public interface FirebaseLoginPresenter {

    void receiveUserLogin(String email, String password);
    void onFailure(String message);
    void onSuccess(FirebaseUser user);
    void attemptToCreateUser(String email, String password);

}
