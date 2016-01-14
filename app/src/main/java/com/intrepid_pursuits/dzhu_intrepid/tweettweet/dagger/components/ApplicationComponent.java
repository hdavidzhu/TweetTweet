package com.intrepid_pursuits.dzhu_intrepid.tweettweet.dagger.components;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed.TweetFeedActivity;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.dagger.modules.ApplicationModule;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.dagger.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(TweetFeedActivity tweetFeedActivity);
}
