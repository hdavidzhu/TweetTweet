package com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.loginAccess;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils.network.RxScheduler;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import rx.Observable;
import rx.Subscriber;

public class LoginAccess {

    private OAuthService service;
    private Token requestToken;

    public LoginAccess() {
        this.service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(BuildConfig.TWITTER_CONSUMER_KEY)
                .apiSecret(BuildConfig.TWITTER_CONSUMER_SECRET)
                .build();
    }

    public void retrieveAuthUrl(Subscriber<String> authUrlSubscriber) {
        getAuthUrlObservable().subscribe(authUrlSubscriber);
    }

    public void retrieveAuthToken(String pin, Subscriber<AccessToken> onAccessTokenReceivedSubscriber) {
        getPinObservable(pin)
                .map(token -> new AccessToken(token.getToken(), token.getSecret()))
                .subscribe(onAccessTokenReceivedSubscriber);
    }

    private Observable<String> getAuthUrlObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    requestToken = service.getRequestToken();
                    String authUrl = service.getAuthorizationUrl(requestToken);
                    subscriber.onNext(authUrl);
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).compose(RxScheduler.applySchedulers());
    }

    private Observable<Token> getPinObservable(String pin) {
        return Observable.create(new Observable.OnSubscribe<Token>() {
            @Override
            public void call(Subscriber<? super Token> subscriber) {
                try {
                    Verifier verifier = new Verifier(pin);
                    Token accessToken = service.getAccessToken(requestToken, verifier);
                    subscriber.onNext(accessToken);
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).compose(RxScheduler.applySchedulers());
    }
}
