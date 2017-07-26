package benforsrup.mvptest.ui.login.interactor;

import java.util.Map;

/**
 * Created by benforsrup on 2017-07-25.
 */

public interface LInteractor {

    void attemptToLogIn(String email, String password);
    void createUser(String email, String password);
}
