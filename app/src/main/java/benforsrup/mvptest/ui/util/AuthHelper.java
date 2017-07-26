package benforsrup.mvptest.ui.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by benforsrup on 2017-07-26.
 */

public class AuthHelper implements FirebaseAuth.AuthStateListener {
    private static final String TAG = "AuthHelper";

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            //user is signed in
            Log.d(TAG, "onAuthStateChanged: user signed in: " + user.getUid());
        } else {
            //User is signed out
            Log.d(TAG, "onAuthStateChanged: user is signed out");
        }
    }

}
