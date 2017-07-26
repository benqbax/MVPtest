package benforsrup.mvptest.ui.main.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import benforsrup.mvptest.ui.main.presenter.MainPresenter;
import benforsrup.mvptest.ui.util.AuthHelper;


/**
 * Created by benforsrup on 2017-07-25.
 */

public class MainInteractor implements MInteractor {
    private FirebaseAuth mAuth;
    private final MainPresenter presenter;
    private static final String TAG = "MainInteractor";


    public MainInteractor(MainPresenter pre) {
        this.presenter = pre;
    }



    @Override
    public void checkIfUserHasLoggedIn() {
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            //user is logged in
            presenter.onLoggedIn(user);
        }
        else{
            //user is not logged in
            presenter.onSignedOut();
        }

    }

    @Override
    public void initilizeAuth() {
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "initilizeAuth: Initilized the authenticator " + mAuth.toString());
    }

    @Override
    public void signOut() {
        mAuth.signOut();
    }


}
