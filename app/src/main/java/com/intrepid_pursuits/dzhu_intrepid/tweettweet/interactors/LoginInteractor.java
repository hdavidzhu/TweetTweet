package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import timber.log.Timber;

public class LoginInteractor {
    public void validateCredentials(String username, String password, OnLoginFinishedListener listener) {
        Timber.d("Validating username and password.");
        Timber.d(username);
        Timber.d(password);
    }
}
