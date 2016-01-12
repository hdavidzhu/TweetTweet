package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.Tweet;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class TweetFeedPresenter implements Callback<List<Tweet>> {
    @Override
    public void onResponse(Response<List<Tweet>> response) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
