package com.example.rickandmorty;

import android.app.Application;

import com.example.rickandmorty.data.db.CharacterDao;
import com.example.rickandmorty.data.db.EpisodesDao;
import com.example.rickandmorty.data.db.LocationDao;
import com.example.rickandmorty.data.db.RoomClient;
import com.example.rickandmorty.data.network.RetrofitClient;
import com.example.rickandmorty.data.network.apiservices.CharacterApiService;
import com.example.rickandmorty.data.network.apiservices.EpisodeApiService;
import com.example.rickandmorty.data.network.apiservices.LocationApiService;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static LocationApiService locationApiService;
    public static EpisodeApiService episodeApiService;
    public static CharacterDao characterDao;
    public static LocationDao locationDao;
    public static EpisodesDao episodesDao;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient retrofitClient = new RetrofitClient();
        RoomClient roomClient = new RoomClient();
        episodeApiService = retrofitClient.providerEpisodeApiService();
        characterApiService = retrofitClient.providerCharacterApiService();
        locationApiService = retrofitClient.providerLocationApiService();
        characterDao = roomClient.provideCharacterDao(roomClient.providerDataBase(this));
        locationDao = roomClient.providerLocationDao(roomClient.providerDataBase(this));
        episodesDao = roomClient.providerEpisode(roomClient.providerDataBase(this));

    }
}
