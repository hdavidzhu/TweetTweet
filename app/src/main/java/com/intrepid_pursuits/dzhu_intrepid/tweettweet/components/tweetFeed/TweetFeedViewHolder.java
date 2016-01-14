package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweet.Tweet;

public class TweetFeedViewHolder extends RecyclerView.ViewHolder {

    private Tweet tweet;

    public TweetFeedViewHolder(TextView itemView) {
        super(itemView);
    }

    public void bindTweet(Tweet tweet) {
        this.tweet = tweet;
        ((TextView) this.itemView).setText(tweet.getText());
    }
}
