package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login.LoginActivity;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors.ServiceGenerator;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors.TwitterAPI;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.Tweet;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils.RxScheduler;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class TweetFeedActivity extends AppCompatActivity implements TweetFeedView {

    private TwitterAPI twitterAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);

        // TODO: Temporary.
        String authToken = getIntent().getStringExtra(LoginActivity.AUTH_TOKEN);
        String authTokenSecret = getIntent().getStringExtra(LoginActivity.AUTH_TOKEN_SECRET);

        if (authToken == null) {
            SharedPreferences sharedPreferences = this.getSharedPreferences(LoginActivity.AUTH_TOKEN_PREFERENCES_LOCATION, Context.MODE_PRIVATE);
            authToken = sharedPreferences.getString(LoginActivity.AUTH_TOKEN, "");
            authTokenSecret = sharedPreferences.getString(LoginActivity.AUTH_TOKEN_SECRET, "");
        }

        twitterAPI = ServiceGenerator.createService(TwitterAPI.class, authToken, authTokenSecret);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Observable<List<Tweet>> tweetFeedObservable = twitterAPI.getTimelineTweets().compose(RxScheduler.applySchedulers());
        tweetFeedObservable.subscribe(new Subscriber<List<Tweet>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Tweet> tweets) {
                List<Tweet> tempTweets = tweets;
            }
        });
    }
}
