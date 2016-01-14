package com.intrepid_pursuits.dzhu_intrepid.tweettweet;

import android.app.Application;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.dagger.components.ApplicationComponent;

import timber.log.Timber;

public class TweetTweetApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

//        // Initialize injector.
//        applicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
    }

//    public ApplicationComponent getApplicationComponent() {
//        return applicationComponent;
//    }
}
