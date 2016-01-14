package com.intrepid_pursuits.dzhu_intrepid.tweettweet;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweet.Tweet;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface TwitterService {
    // Reference at https://dev.twitter.com/rest/reference/get/statuses/home_timeline
    @GET("/1.1/statuses/home_timeline.json")
    Observable<List<Tweet>> getTimelineTweets();
}
