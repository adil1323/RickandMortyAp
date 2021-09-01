package com.example.rickandmorty.data.network.apiservices;

import com.example.rickandmorty.model.CharacterModel;
import com.example.rickandmorty.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterApiService {
    @GET("api/character")
    Call<RickAndMortyResponse<CharacterModel>> fetchCharacters();

    @GET("api/character/{id}")
    Call<CharacterModel> fetchCharacterId(@Path("id") int id);
}
