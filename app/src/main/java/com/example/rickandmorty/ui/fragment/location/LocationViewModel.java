package com.example.rickandmorty.ui.fragment.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.App;
import com.example.rickandmorty.base.BaseViewModel;
import com.example.rickandmorty.model.CharacterModel;
import com.example.rickandmorty.model.LocationModel;
import com.example.rickandmorty.model.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends BaseViewModel {

    public MutableLiveData<RickAndMortyResponse<LocationModel>> fetchLocation() {
        MutableLiveData<RickAndMortyResponse<LocationModel>> liveData = new MutableLiveData<>();
        App.locationApiService.fetchLocation().enqueue(new Callback<RickAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<LocationModel>> call, Response<RickAndMortyResponse<LocationModel>> response) {
                if (response.body() != null) {
                    App.locationDao.insertAll(response.body().getResults());
                    liveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<LocationModel>> call, Throwable t) {
                liveData.setValue(null);
            }
        });
        return liveData;
    }

    public ArrayList<LocationModel> getLocation() {
        return (ArrayList<LocationModel>) App.locationDao.getAll();
    }

}

