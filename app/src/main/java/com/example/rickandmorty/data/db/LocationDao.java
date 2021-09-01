package com.example.rickandmorty.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorty.model.CharacterModel;
import com.example.rickandmorty.model.LocationModel;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;
@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<LocationModel> locationModel);

    @Query("SELECT * FROM locationModel ")
    List<LocationModel> getAll();
}
