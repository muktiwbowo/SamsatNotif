package com.dabudabu.samsatnotif.service;

import android.app.Application;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIHelper {
    public static APIService ServiceApi(Application application){
        Cache okHttpCache = new Cache(application.getCacheDir(), 10 * 1024 * 1024);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(okHttpCache);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIService.class);
    }
}
