package com.intrepid_pursuits.dzhu_intrepid.tweettweet;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

// https://futurestud.io/blog/retrofit-token-authentication-on-android
public class ServiceGenerator {
    public static final String API_BASE_URL = "https://api.twitter.com";
    public static OkHttpOAuthConsumer consumer;
    public static OkHttpClient httpClient;
    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken, final String authTokenSecret) {
        consumer = new OkHttpOAuthConsumer(
                BuildConfig.TWITTER_CONSUMER_KEY,
                BuildConfig.TWITTER_CONSUMER_SECRET);

        if (authToken != null && authTokenSecret != null) {
            consumer.setTokenWithSecret(authToken, authTokenSecret);
        }

        httpClient = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        Retrofit retrofit = builder
                .client(httpClient)
                .build();
        return retrofit.create(serviceClass);
    }
}
