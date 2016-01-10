package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class TweetFeedInteractor {
    public void loadTweetsFromHome() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://https://api.twitter.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Prepare call.
        TwitterAPI twitterAPI = retrofit.create(TwitterAPI.class);

        Call<List<Tweet>> call = twitterAPI.getTimelineTweets();
        // call.enqueue();
    }
}
