package benforsrup.mvptest.ui.login.view;

/**
 * Created by benforsrup on 2017-07-25.
 */

public interface LoginView {

    void logTheUserIn(String username, String uid);
    void onFailure(String message);
}
