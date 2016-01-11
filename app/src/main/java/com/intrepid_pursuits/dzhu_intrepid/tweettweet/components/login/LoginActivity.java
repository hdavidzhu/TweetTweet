package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

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
        this.presenter.attemptLogin();
    }
}
