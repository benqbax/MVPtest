package benforsrup.mvptest.ui.login.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import benforsrup.mvptest.R;
import benforsrup.mvptest.ui.login.presenter.FirebaseLoginPresenterImpl;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, SignUpView{


    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mSignUpButton;
    private TextView mLoginLink;
    private FirebaseLoginPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        createUI();


    }

    private void createUI() {
        mPresenter = new FirebaseLoginPresenterImpl(null, this);
        mPresenter.initilizeAuth();

        mLoginLink = (TextView) findViewById(R.id.link_login);
        mLoginLink.setOnClickListener(this);

        mSignUpButton = (Button) findViewById(R.id.btn_signup);
        mSignUpButton.setOnClickListener(this);


        mEmailText = (EditText) findViewById(R.id.input_email);
        mPasswordText = (EditText) findViewById(R.id.input_password);

    }

    @Override
    public void onClick(View v) {
        if (v==mLoginLink){
            finish();
        }
        if(v==mSignUpButton){
            mPresenter.attemptToCreateUser(mEmailText.getText().toString(), mPasswordText.getText().toString());
        }
    }

    @Override
    public void onSuccessCreatedUser() {
        finish();
    }
}
