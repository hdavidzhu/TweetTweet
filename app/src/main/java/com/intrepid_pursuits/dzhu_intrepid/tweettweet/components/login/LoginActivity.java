package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;

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

    // From: http://stackoverflow.com/questions/12673862/how-to-handle-oauth-url-callbacks-with-intent-filters-if-authentication-is-done
    @Override
    public void openTwitterAuthWindow(String authUrl) {
        WebView webView = new WebView(this);
        webView.requestFocus(View.FOCUS_DOWN);
        webView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });

        webView.loadUrl(authUrl);
        setContentView(webView);
    }
}
