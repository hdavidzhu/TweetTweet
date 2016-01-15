package com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.tweetFeed;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.User;

public class Tweet {
    private long id;
    private String createdAt;
    private String text;
    private User user;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }
}
