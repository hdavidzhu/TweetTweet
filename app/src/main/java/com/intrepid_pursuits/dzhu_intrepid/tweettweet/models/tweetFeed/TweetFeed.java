package com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweetFeed;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils.network.RxScheduler;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils.network.TwitterService;

import java.util.List;

import rx.Subscriber;

public class TweetFeed {
    private TwitterService twitterService;

    public TweetFeed(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    public void retrieveTimelineTweets(Subscriber<List<Tweet>> tweetListSubscriber) {
        twitterService
                .getTimelineTweets()
                .compose(RxScheduler.applySchedulers())
                .subscribe(tweetListSubscriber);
    }
}
