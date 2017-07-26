package benforsrup.mvptest.ui.login.view;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by benforsrup on 2017-07-25.
 */

public interface LoginView {

    void logTheUserIn(FirebaseUser user);
    void onFailure(String message);
}
