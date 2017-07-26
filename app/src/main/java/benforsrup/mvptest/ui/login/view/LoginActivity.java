package benforsrup.mvptest.ui.login.view;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import benforsrup.mvptest.R;
import benforsrup.mvptest.ui.login.presenter.FirebaseLoginPresenterImpl;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{


    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private FirebaseLoginPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createUI();
    }

    private void createUI() {
        mEmailEditText = (EditText) findViewById(R.id.input_email);
        mPasswordEditText = (EditText) findViewById(R.id.input_password);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mLoginButton.setOnClickListener(this);
        mPresenter = new FirebaseLoginPresenterImpl(this);
        mPresenter.initilizeAuth();
    }


    @Override
    public void logTheUserIn(String username, String uid) {
        Toast.makeText(this, username + " logged in with the uid:" + uid, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        mPresenter.receiveUserLogin(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.registerListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unregisterListener();
    }
}
