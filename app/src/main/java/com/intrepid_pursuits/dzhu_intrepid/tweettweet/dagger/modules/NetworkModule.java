package com.intrepid_pursuits.dzhu_intrepid.tweettweet.dagger.modules;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors.TwitterService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides @Singleton
    OkHttpOAuthConsumer provideOkHttpOAuthConsumer(String authToken, String authTokenSecret) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(
                BuildConfig.TWITTER_CONSUMER_KEY,
                BuildConfig.TWITTER_CONSUMER_SECRET);

        consumer.setTokenWithSecret(authToken, authTokenSecret);

        return consumer;
    }

    @Provides @Singleton
    OkHttpClient provideOkHttpClient(OkHttpOAuthConsumer consumer) {
        return new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();
    }

    @Provides @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides @Singleton
    TwitterService provideTwitterService(Retrofit retrofit) {
        return retrofit.create(TwitterService.class);
    }
}
