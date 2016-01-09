package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors.LoginInteractor;

// Adapted from https://github.com/jpotts18/android-mvp/blob/master/app/src/main/java/io/jpotts18/android_mvp/domain/login/LoginPresenter.java
public class LoginPresenter implements OnLoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractor();
    }

    public void attemptLogin(String username, String password) {
//        loginInteractor.validateCredentials(username, password, this);
    }
}
