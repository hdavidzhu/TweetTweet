package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login;

public interface LoginView {
    void openTwitterAuthWindow(String authUrl);
    void switchToTweetFeed(String authToken, String authTokenSecret);
}
