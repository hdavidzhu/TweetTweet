package com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors;

import android.os.Looper;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class LoginInteractor {
    public void validateCredentials(Subscriber<String> authUrlSubscriber) {
        Timber.d("Button clicked.");
        getAuthUrlObservable().subscribe(authUrlSubscriber);
    }

    private Observable<String> getAuthUrlObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    OAuthService service = new ServiceBuilder()
                            .provider(TwitterApi.class)
                            .apiKey(BuildConfig.TWITTER_CONSUMER_KEY)
                            .apiSecret(BuildConfig.TWITTER_CONSUMER_SECRET)
                            .build();

                    Boolean isOnUIThread = Looper.myLooper() == Looper.getMainLooper();
                    Timber.d("Am I on the UI thread? " + isOnUIThread.toString());

                    Token requestToken = service.getRequestToken();
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
}
