package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors.LoginInteractor;

import rx.Subscriber;
import timber.log.Timber;

// Adapted from https://github.com/jpotts18/android-mvp/blob/master/app/src/main/java/io/jpotts18/android_mvp/domain/login/LoginPresenter.java
public class LoginPresenter {
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private Subscriber<String> authUrlSubscriber;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractor();

        this.authUrlSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Timber.d("The job is done.");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.toString());
            }

            @Override
            public void onNext(String authUrl) {
                Timber.d(authUrl);
                LoginPresenter.this.loginView.openTwitterAuthWindow(authUrl);
            }
        };
    }

    public void attemptLogin() {
        loginInteractor.validateCredentials(this.authUrlSubscriber);
    }
}
