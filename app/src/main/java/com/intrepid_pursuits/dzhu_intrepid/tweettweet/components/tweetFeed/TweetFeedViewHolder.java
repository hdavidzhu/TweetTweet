package com.intrepid_pursuits.dzhu_intrepid.tweettweet.components.tweetFeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.R;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweetFeed.Tweet;

public class TweetFeedViewHolder extends RecyclerView.ViewHolder {
    public TweetFeedViewHolder(View itemView) {
        super(itemView);
    }

    public void bindTweet(Tweet tweet) {
        ((TextView) this.itemView.findViewById(R.id.date)).setText(tweet.getCreatedAt());
        ((TextView) this.itemView.findViewById(R.id.name)).setText(tweet.getUser().getName());
        ((TextView) this.itemView.findViewById(R.id.tweet_text)).setText(tweet.getText());
    }
}
