package com.intrepid_pursuits.dzhu_intrepid.tweettweet;

import android.app.Application;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.di.components.ApplicationComponent;

import timber.log.Timber;

public class TweetTweetApplication extends Application {

    private ApplicationComponent applicationComponent;

//    private LoginComponent loginComponent;
//    private UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        // Initialize injector.
//        applicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .networkModule(new TwitterModule("https://api.twitter.com"))
//                .build();
    }

//    public ApplicationComponent getApplicationComponent() {
//        return applicationComponent;
//    }

//    public UserComponent createUserComponent(AccessToken accessToken) {
//        userComponent = applicationComponent.plus(new TwitterModule(accessToken));
//    }
}
