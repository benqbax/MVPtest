package benforsrup.mvptest.ui.login.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import benforsrup.mvptest.R;
import benforsrup.mvptest.ui.login.presenter.FirebaseLoginPresenterImpl;
import benforsrup.mvptest.ui.registration.view.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{
    private static final String TAG = "LoginActivity";


    static final int SIGN_UP_REQUEST = 1;

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private TextView mSignUp;
    private FirebaseLoginPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createUI();
        mPresenter = new FirebaseLoginPresenterImpl(this);
    }

    private void createUI() {
        mEmailEditText = (EditText) findViewById(R.id.input_email);
        mPasswordEditText = (EditText) findViewById(R.id.input_password);
        mSignUp = (TextView) findViewById(R.id.link_signup);
        mSignUp.setOnClickListener(this);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(this);

    }


    @Override
    public void logTheUserIn(FirebaseUser user) {
        Toast.makeText(this, user + " logged in with the uid:" + user.getUid(), Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
        hideSoftKeyboard(this);
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: " + view);
        if (view == mLoginButton){
            Log.d(TAG, "onClick: Login clicked");
            mPresenter.receiveUserLogin(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
        }
        if(view == mSignUp){
            Log.d(TAG, "onClick: Sign up!");
            Intent intent = new Intent(this, SignUpActivity.class);


//



            startActivityForResult(intent, SIGN_UP_REQUEST);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SIGN_UP_REQUEST:
                if(resultCode == Activity.RESULT_OK){
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
