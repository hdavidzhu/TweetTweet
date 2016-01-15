package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed.TweetFeedActivity;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.loginAccess.AccessToken;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.loginAccess.LoginAccess;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    public static final String AUTH_TOKEN_PREFERENCES_LOCATION = "AUTH_TOKEN_PREFERENCES_LOCATION";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";
    public static final String AUTH_TOKEN_SECRET = "AUTH_TOKEN_SECRET";
    @Bind(R.id.pin_input)
    EditText pinInput;
    private LoginAccess loginAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginAccess = new LoginAccess();
    }

    @OnClick(R.id.login_submit)
    public void loginButtonClicked() {
        this.attemptLogin();
    }

    @OnClick(R.id.pin_submit)
    public void pinButtonClicked() {
        this.submitPin(pinInput.getText().toString());
    }

    @OnClick(R.id.temporary_login)
    public void switchToTweetFeed() {
        Intent intent = new Intent(this, TweetFeedActivity.class);
        startActivity(intent);
        finish();
    }

    public void openTwitterAuthWindow(String authUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(authUrl));
        startActivity(intent);
    }

    public void switchToTweetFeed(AccessToken accessToken) {
        Boolean hasSaved = saveAuthToken(accessToken);
        if (hasSaved) {
            switchToTweetFeed();
        }
    }

    public Boolean saveAuthToken(AccessToken accessToken) {
        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences(AUTH_TOKEN_PREFERENCES_LOCATION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTH_TOKEN, accessToken.getToken());
        editor.putString(AUTH_TOKEN_SECRET, accessToken.getSecret());
        return editor.commit();
    }

    public void attemptLogin() {
        loginAccess.retrieveAuthUrl(this.onAuthUrlRetrieved());
    }

    public void submitPin(String pin) {
        loginAccess.retrieveAuthToken(pin, this.onAccessTokenRetrieved());
    }

    private Subscriber<String> onAuthUrlRetrieved() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e.toString());
            }

            @Override
            public void onNext(String authUrl) {
                Timber.d(authUrl);
                openTwitterAuthWindow(authUrl);
            }
        };
    }

    private Subscriber<AccessToken> onAccessTokenRetrieved() {
        return new Subscriber<AccessToken>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e.toString());
            }

            @Override
            public void onNext(AccessToken token) {
                switchToTweetFeed(token);
            }
        };
    }
}
