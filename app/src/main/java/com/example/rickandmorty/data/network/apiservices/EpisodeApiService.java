package com.example.rickandmorty.data.network.apiservices;

import com.example.rickandmorty.model.EpisodeModel;
import com.example.rickandmorty.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EpisodeApiService {
    @GET("api/episode")
    Call<RickAndMortyResponse<EpisodeModel>> fetchEpisode();
}
