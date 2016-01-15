package com.intrepid_pursuits.dzhu_intrepid.tweettweet;

import android.app.Application;

import timber.log.Timber;

public class TweetTweetApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
