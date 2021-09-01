package com.example.rickandmorty.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorty.model.CharacterModel;
import com.example.rickandmorty.model.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<EpisodeModel> episodeModels);

    @Query("SELECT * FROM episodemodel ")
    List<EpisodeModel> getAll();

}
