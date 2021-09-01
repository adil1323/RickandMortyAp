package com.example.rickandmorty.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rickandmorty.model.CharacterModel;
import com.example.rickandmorty.model.EpisodeModel;
import com.example.rickandmorty.model.LocationModel;

@Database(entities = {CharacterModel.class, LocationModel.class, EpisodeModel.class}, version = 3, exportSchema = false)
abstract class AppDataBase extends RoomDatabase {

    public abstract CharacterDao characterDao();

    public abstract LocationDao locationDao();

    public abstract EpisodesDao episodesDao();
}

