package benforsrup.mvptest.ui.registration.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import benforsrup.mvptest.R;
import benforsrup.mvptest.ui.login.presenter.FirebaseLoginPresenterImpl;
import benforsrup.mvptest.ui.registration.presenter.SignUpPresenter;
import benforsrup.mvptest.ui.registration.presenter.SignUpPresenterImpl;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, SignUpView{


    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mSignUpButton;
    private TextView mLoginLink;
    private EditText mUsername;


    //presenter
    private SignUpPresenterImpl mSignUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mSignUpPresenter = new SignUpPresenterImpl(this);
        createUI();

    }

    private void createUI() {

        mLoginLink = (TextView) findViewById(R.id.link_login);
        mLoginLink.setOnClickListener(this);

        mSignUpButton = (Button) findViewById(R.id.btn_signup);
        mSignUpButton.setOnClickListener(this);


        mEmailText = (EditText) findViewById(R.id.input_email);
        mPasswordText = (EditText) findViewById(R.id.input_password);

        mUsername = (EditText) findViewById(R.id.input_name);

    }

    @Override
    public void onClick(View v) {
        if (v==mLoginLink){
            finish();
        }
        if(v==mSignUpButton){
            mSignUpPresenter.attemptToCreateUser(mUsername.getText().toString(), mEmailText.getText().toString(), mPasswordText.getText().toString());
        }
    }

    @Override
    public void onSuccessCreatedUser() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
