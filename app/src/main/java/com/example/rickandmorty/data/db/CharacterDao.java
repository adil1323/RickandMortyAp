package com.example.rickandmorty.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorty.model.CharacterModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<CharacterModel> characterModel);

    @Query("SELECT * FROM charactermodel ")
    List<CharacterModel> getAll();

}
