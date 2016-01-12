package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import android.content.SharedPreferences;
import android.os.Looper;
import android.preference.PreferenceManager;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils.RxScheduler;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import rx.Observable;
import rx.Subscriber;
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
                .map(tokenArray -> {
//                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences();
                    return tokenArray;
                })
                .subscribe(onAccessTokenReceivedSubscriber)
    }

    private Observable<String> getAuthUrlObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
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
        }).compose(RxScheduler.applySchedulers());
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
        }).compose(RxScheduler.applySchedulers());
    }
}
