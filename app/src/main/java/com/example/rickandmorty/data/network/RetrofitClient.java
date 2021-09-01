package com.example.rickandmorty.data.network;

import com.example.rickandmorty.data.network.apiservices.CharacterApiService;
import com.example.rickandmorty.data.network.apiservices.EpisodeApiService;
import com.example.rickandmorty.data.network.apiservices.LocationApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(provideLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    private Retrofit providerRetrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public CharacterApiService providerCharacterApiService() {
        return providerRetrofit.create(CharacterApiService.class);
    }

    public LocationApiService providerLocationApiService() {
        return providerRetrofit.create(LocationApiService.class);
    }

    public EpisodeApiService providerEpisodeApiService() {
        return providerRetrofit.create(EpisodeApiService.class);
    }
}