package benforsrup.mvptest.ui.main.view;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by benforsrup on 2017-07-25.
 */

public interface MainView {
    void logtheUserIn(FirebaseUser user);
    void onSignedOut();
    void checkStatus();
}
