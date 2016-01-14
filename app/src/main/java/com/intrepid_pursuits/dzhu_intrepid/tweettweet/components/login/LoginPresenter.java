package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.loginAccess.AccessToken;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.loginAccess.LoginAccess;

import rx.Subscriber;
import timber.log.Timber;

// Adapted from https://github.com/jpotts18/android-mvp/blob/master/app/src/main/java/io/jpotts18/android_mvp/domain/login/LoginPresenter.java
public class LoginPresenter {
    private LoginView loginView;
    private LoginAccess loginAccess;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.loginAccess = new LoginAccess();
    }

    public void attemptLogin() {
        loginAccess.retrieveAuthUrl(this.onAuthUrlRetrieved());
    }

    public void submitPin(String pin) {
        loginAccess.submitPin(pin, this.onAccessTokenRetrieved());
    }

    // TODO: A new instance of this subscriber is created every single time. Should this be an observer
    // TODO: and be reused?
    // Reference: http://stackoverflow.com/a/30222908/2204868
    private Subscriber<String> onAuthUrlRetrieved() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                Timber.e(e.toString());
            }

            @Override
            public void onNext(String authUrl) {
                Timber.d(authUrl);
                loginView.openTwitterAuthWindow(authUrl);
            }
        };
    }

    private Subscriber<AccessToken> onAccessTokenRetrieved() {
        return new Subscriber<AccessToken>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                Timber.e(e.toString());
            }

            @Override
            public void onNext(AccessToken token) {
                loginView.switchToTweetFeed(token.getToken(), token.getSecret());
            }
        };
    }
}
