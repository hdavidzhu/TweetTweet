package com.intrepid_pursuits.dzhu_intrepid.tweettweet.di.components;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed.TweetFeedActivity;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.di.modules.ApplicationModule;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.di.modules.TwitterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TwitterModule.class})
public interface ApplicationComponent {
    void inject(TweetFeedActivity tweetFeedActivity);
}
