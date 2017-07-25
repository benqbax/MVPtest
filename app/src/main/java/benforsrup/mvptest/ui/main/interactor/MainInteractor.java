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



/**
 * Created by benforsrup on 2017-07-25.
 */

public class MainInteractor implements MInteractor {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private final MainPresenter presenter;
    private static final String TAG = "MainInteractor";


    public MainInteractor(MainPresenter pre) {
        this.presenter = pre;
    }



    @Override
    public void checkIfUserHasLoggedIn() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                Log.d(TAG, "onAuthStateChanged: state");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    //user is signed in
                    Log.d(TAG, "onAuthStateChanged: user signed in: " + user.getUid());
                    presenter.onLoggedIn(user);
                }
                else{
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged: user is signed out");
                    presenter.onSignedOut();
                }
            }
        };

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

    @Override
    public void register() {
        mAuth.addAuthStateListener(mAuthStateListener);
        Log.d(TAG, "register: registered the listener");
    }


    @Override
    public void unregister() {
        if(mAuthStateListener!=null){
            mAuth.removeAuthStateListener(mAuthStateListener);
            Log.d(TAG, "unregister: unregistered the listener");
        }
    }



}
