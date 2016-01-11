package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TwitterAPI {

//    @GET("oauth/authenticate")
//    Call<> login();

    // Reference at https://dev.twitter.com/rest/reference/get/statuses/home_timeline
    @GET("/1.1/statuses/home_timeline.json")
    Call<List<Tweet>> getTimelineTweets();
}
