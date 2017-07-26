package benforsrup.mvptest.ui.registration.presenter;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by benforsrup on 2017-07-26.
 */

public interface SignUpPresenter {
    void attemptToCreateUser(String username, String email, String password);
    void userCreated(FirebaseUser user);
}
