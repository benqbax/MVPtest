package benforsrup.mvptest.ui.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import benforsrup.mvptest.ui.login.view.LoginActivity;
import benforsrup.mvptest.R;
import benforsrup.mvptest.ui.main.presenter.MainActivityPresenterImpl;
import benforsrup.mvptest.ui.main.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {
    private static final String TAG = "MainActivity";
    private MainPresenter mMainPresenter;
    private Button mLogoutButton;
    private TextView mUserText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createUI();

        //initilizes authentication
        mMainPresenter = new MainActivityPresenterImpl(this);
        mMainPresenter.initilizeAuth();


        checkStatus();

    }

    @Override
    public void onClick(View view) {
        mMainPresenter.signUserOut();
        checkStatus();
    }


    private void createUI(){

        mLogoutButton = (Button) findViewById(R.id.button_logout);
        mLogoutButton.setOnClickListener(this);
        mUserText = (TextView) findViewById(R.id.user_text);

    }


    @Override
    public void userIsLoggedIn(FirebaseUser user) {
        Log.d(TAG, "logtheUserIn: Email: " + user.getEmail());
        mUserText.setText(user.getEmail());
    }

    @Override
    public void onSignedOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
       // Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkStatus() {
        mMainPresenter.checkIfUserHasLoggedIn();
    }

}
