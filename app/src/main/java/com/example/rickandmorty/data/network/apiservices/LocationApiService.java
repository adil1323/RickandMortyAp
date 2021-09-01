package com.example.rickandmorty.data.network.apiservices;

import com.example.rickandmorty.model.LocationModel;
import com.example.rickandmorty.model.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiService {
    @GET("api/location")
    Call<RickAndMortyResponse<LocationModel>> fetchLocation();

}
