package com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.loginAccess;

public class AccessToken {
    private String token;
    private String secret;

    public AccessToken(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public String getSecret() {
        return secret;
    }
}
