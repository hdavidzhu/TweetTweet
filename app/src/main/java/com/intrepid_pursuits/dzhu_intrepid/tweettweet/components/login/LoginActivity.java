package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed.TweetFeedActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    public static final String AUTH_TOKEN_PREFERENCES_LOCATION = "AUTH_TOKEN_PREFERENCES_LOCATION";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";
    public static final String AUTH_TOKEN_SECRET = "AUTH_TOKEN_SECRET";

    private LoginPresenter presenter;

    @Bind(R.id.pin_input)
    EditText pinInput;

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

    @OnClick(R.id.pin_submit)
    public void pinButtonClicked() {
        this.presenter.submitPin(pinInput.getText().toString());
    }

    // From: http://stackoverflow.com/a/3004542/2204868
    @Override
    public void openTwitterAuthWindow(String authUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(authUrl));
        startActivity(intent);
    }

    @Override
    public void switchToTweetFeed(String authToken, String authTokenSecret) {

        // TODO: Temporary.
        saveAuthToken(authToken, authTokenSecret);

        Intent intent = new Intent(this, TweetFeedActivity.class);
        intent.putExtra("AUTH_TOKEN", authToken);
        intent.putExtra("AUTH_TOKEN_SECRET", authTokenSecret);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.temporary_login)
    public void switchToTweetFeed() {
        Intent intent = new Intent(this, TweetFeedActivity.class);
        startActivity(intent);
        finish();
    }

    // TODO: This is temporary. We can save the info to shared prefs at this moment.
    public void saveAuthToken(String authToken, String authTokenSecret) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(AUTH_TOKEN_PREFERENCES_LOCATION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTH_TOKEN, authToken);
        editor.putString(AUTH_TOKEN_SECRET, authTokenSecret);
        editor.apply();
    }
}
