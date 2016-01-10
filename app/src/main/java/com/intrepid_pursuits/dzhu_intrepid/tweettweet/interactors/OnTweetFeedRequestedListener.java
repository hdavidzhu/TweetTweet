package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.Tweet;

import java.util.List;

import retrofit2.Callback;

public interface OnTweetFeedRequestedListener extends Callback<List<Tweet>> {
}
