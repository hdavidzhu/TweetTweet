package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import android.os.Looper;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class LoginInteractor {

    private OAuthService service;
    private Token requestToken;

    public LoginInteractor() {
        this.service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(BuildConfig.TWITTER_CONSUMER_KEY)
                .apiSecret(BuildConfig.TWITTER_CONSUMER_SECRET)
                .build();
    }

    public void retrieveAuthUrl(Subscriber<String> authUrlSubscriber) {
        Timber.d("Button clicked.");
        getAuthUrlObservable().subscribe(authUrlSubscriber);
    }

    public void submitPin(String pin, Subscriber<String[]> onAccessTokenReceivedSubscriber) {
        Timber.d(pin);
        getPinObservable(pin)
                .map(accessToken -> new String[]{accessToken.getToken(), accessToken.getSecret()})
                .subscribe(onAccessTokenReceivedSubscriber);
    }

    private Observable<String> getAuthUrlObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Boolean isOnUIThread = Looper.myLooper() == Looper.getMainLooper();
                    Timber.d("Am I on the UI thread? " + isOnUIThread.toString());

                    requestToken = service.getRequestToken();
                    String authUrl = service.getAuthorizationUrl(requestToken);
                    Timber.d(authUrl);

                    subscriber.onNext(authUrl);

                } catch (Throwable e) {
                    subscriber.onError(e);
                }

                subscriber.onCompleted();
            }
        });

        // TODO: Potentially abstract this out.
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Token> getPinObservable(String pin) {
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
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
