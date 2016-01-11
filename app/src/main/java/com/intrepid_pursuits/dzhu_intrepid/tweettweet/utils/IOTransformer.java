package com.intrepid_pursuits.dzhu_intrepid.tweettweet.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// Source: https://github.com/appfoundry/android-mosby-rx/blob/master/app/src/main/java/be/appfoundry/rx/SimpleRxUtil.java
// This method helps manage thread toggling between IO and UI tasks.
public class IOTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
