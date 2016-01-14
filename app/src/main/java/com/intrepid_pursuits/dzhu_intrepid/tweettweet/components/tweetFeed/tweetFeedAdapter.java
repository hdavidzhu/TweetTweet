package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweet.Tweet;

import java.util.List;

public class TweetFeedAdapter extends RecyclerView.Adapter<TweetFeedViewHolder> {

    private List<Tweet> tweets;

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
        notifyDataSetChanged();
    }

    @Override
    public TweetFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tweetView = (TextView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_tweet, parent, false);
        return new TweetFeedViewHolder(tweetView);
    }

    @Override
    public int getItemCount() {
        return tweets == null ? 0 : tweets.size();
    }

    @Override
    public void onBindViewHolder(TweetFeedViewHolder holder, int position) {
        holder.bindTweet(tweets.get(position));
    }
}

