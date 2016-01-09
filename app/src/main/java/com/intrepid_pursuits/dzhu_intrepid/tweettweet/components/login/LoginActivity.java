package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.login_username)
    EditText username;

    @Bind(R.id.login_password)
    EditText password;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        this.presenter = new LoginPresenter(this);
    }

    @OnClick(R.id.login_submit)
    public void loginButtonClicked() {
        this.presenter.attemptLogin(
                username.getText().toString(),
                password.getText().toString());
    }
}
