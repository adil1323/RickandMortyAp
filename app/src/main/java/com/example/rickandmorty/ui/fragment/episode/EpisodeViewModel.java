package com.example.rickandmorty.ui.fragment.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.App;
import com.example.rickandmorty.base.BaseViewModel;
import com.example.rickandmorty.model.EpisodeModel;
import com.example.rickandmorty.model.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends BaseViewModel {

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisode() {
        MutableLiveData<RickAndMortyResponse<EpisodeModel>> epiData = new MutableLiveData<>();
        App.episodeApiService.fetchEpisode().enqueue(new Callback<RickAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<EpisodeModel>> call, Response<RickAndMortyResponse<EpisodeModel>> response) {
                if (response.body() != null) {
                  App.episodesDao.insertAll(response.body().getResults());
                    epiData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<EpisodeModel>> call, Throwable t) {
                epiData.setValue(null);
            }
        });
        return epiData;

    }
    ArrayList<EpisodeModel> getEpisode(){
        return (ArrayList<EpisodeModel>) App.episodesDao.getAll();
    }

}

