package com.example.rickandmorty.data.db;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {

    public AppDataBase providerDataBase(Context context) {

        return Room
                .databaseBuilder(context, AppDataBase.class, "rick-and-morty-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public CharacterDao provideCharacterDao(AppDataBase dataBase) {
        return dataBase.characterDao();
    }

    public LocationDao providerLocationDao(AppDataBase dataBase) {
        return dataBase.locationDao();
    }
    public EpisodesDao providerEpisode(AppDataBase dataBase){
        return dataBase.episodesDao();
    }
}
