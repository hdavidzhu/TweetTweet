package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class LoginInteractor {
    public void validateCredentials() {
        Timber.d("Button clicked.");
        Subscription subscription = getObservable().subscribe(getSubscriber());
    }

    public Observable<String> retrieveAuthUrl() {
        OAuthService service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(BuildConfig.TWITTER_CONSUMER_KEY)
                .apiSecret(BuildConfig.TWITTER_CONSUMER_SECRET)
                .build();

        Token requestToken = service.getRequestToken();
        String authUrl = service.getAuthorizationUrl(requestToken);
        Timber.d(authUrl);

        return Observable.just(authUrl);
    }

    private Observable<String> getObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OAuthService service = new ServiceBuilder()
                        .provider(TwitterApi.class)
                        .apiKey(BuildConfig.TWITTER_CONSUMER_KEY)
                        .apiSecret(BuildConfig.TWITTER_CONSUMER_SECRET)
                        .build();

                Token requestToken = service.getRequestToken();
                String authUrl = service.getAuthorizationUrl(requestToken);
                Timber.d(authUrl);

                subscriber.onNext(authUrl);
                subscriber.onCompleted();
            }
        });

        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Subscriber<String> getSubscriber() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Timber.d("The job is done.");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.toString());
            }

            @Override
            public void onNext(String s) {
                Timber.d(s);
            }
        };
    }
}
