package com.intrepid_pursuits.dzhu_intrepid.tweettweet.di.modules;

import com.intrepid_pursuits.dzhu_intrepid.tweettweet.BuildConfig;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.interactors.TwitterService;
import com.intrepid_pursuits.dzhu_intrepid.tweettweet.models.loginAccess.AccessToken;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

@Module
public class TwitterModule {

    private AccessToken accessToken;

    public TwitterModule(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Provides @Singleton
    OkHttpOAuthConsumer provideOkHttpOAuthConsumer() {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(
                BuildConfig.TWITTER_CONSUMER_KEY,
                BuildConfig.TWITTER_CONSUMER_SECRET);
        consumer.setTokenWithSecret(accessToken.getToken(), accessToken.getSecret());
        return consumer;
    }

    @Provides @Singleton
    OkHttpClient provideOkHttpClient(OkHttpOAuthConsumer consumer) {
        return new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();
    }

    @Provides @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, @Named("baseUrl") String baseUrl) {
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
