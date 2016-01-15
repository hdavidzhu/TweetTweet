package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.login.LoginActivity;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweetFeed.TweetFeed;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils.network.TwitterServiceGenerator;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils.network.TwitterService;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweetFeed.Tweet;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class TweetFeedActivity extends AppCompatActivity {

    private TweetFeed tweetFeed;
    private TweetFeedAdapter tweetFeedAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_feed);
        ButterKnife.bind(this);

        sharedPreferences = this.getSharedPreferences(
                LoginActivity.AUTH_TOKEN_PREFERENCES_LOCATION,
                Context.MODE_PRIVATE);

        attachTweetFeedView();
        TwitterService twitterService = createTwitterService();
        this.tweetFeed = generateTweetFeed(twitterService);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getTimelineTweets();
    }

    @OnClick(R.id.logout)
    public void logoutButtonClicked() {
        deleteSession();
        goToLogin();
    }

    private void attachTweetFeedView() {
        RecyclerView tweetFeedView = (RecyclerView) findViewById(R.id.tweet_feed);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        tweetFeedView.setLayoutManager(layoutManager);
        tweetFeedAdapter = new TweetFeedAdapter();
        tweetFeedView.setAdapter(tweetFeedAdapter);
    }

    private TwitterService createTwitterService() {
        String authTokenKey = sharedPreferences.getString(LoginActivity.AUTH_TOKEN, "");
        String authTokenSecret = sharedPreferences.getString(LoginActivity.AUTH_TOKEN_SECRET, "");
        return TwitterServiceGenerator.createService(authTokenKey, authTokenSecret);
    }

    private TweetFeed generateTweetFeed(TwitterService twitterService) {
        return new TweetFeed(twitterService);
    }

    private void getTimelineTweets() {
        this.tweetFeed.retrieveTimelineTweets(onTweetListRetrieved());
    }

    private void deleteSession() {
        sharedPreferences.edit().clear().apply();
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private Subscriber<List<Tweet>> onTweetListRetrieved() {
        return new Subscriber<List<Tweet>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Tweet> tweets) {
                tweetFeedAdapter.setTweets(tweets);
            }
        };
    }
}
