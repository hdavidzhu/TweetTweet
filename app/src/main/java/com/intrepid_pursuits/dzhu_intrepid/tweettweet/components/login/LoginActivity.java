package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import android.content.Intent;
import android.net.Uri;
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

    // From: http://stackoverflow.com/a/3004542/2204868
    @Override
    public void openTwitterAuthWindow(String authUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(authUrl));
        startActivity(intent);
    }
}
