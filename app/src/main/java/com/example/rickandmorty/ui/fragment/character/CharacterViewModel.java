package com.example.rickandmorty.ui.fragment.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.App;
import com.example.rickandmorty.base.BaseViewModel;
import com.example.rickandmorty.model.CharacterModel;
import com.example.rickandmorty.model.RickAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends BaseViewModel {

    MutableLiveData<RickAndMortyResponse<CharacterModel>> fetchCharacter() {
        MutableLiveData<RickAndMortyResponse<CharacterModel>> data = new MutableLiveData<>();
        App.characterApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<CharacterModel>> call, Response<RickAndMortyResponse<CharacterModel>> response) {
                if (response.body() != null) {
                    App.characterDao.insertAll(response.body().getResults());
                    data.setValue((response.body()));

                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<CharacterModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<CharacterModel> fetchId(int id) {
        MutableLiveData<CharacterModel> dataId = new MutableLiveData<>();
        App.characterApiService.fetchCharacterId(id).enqueue(new Callback<CharacterModel>() {
            @Override
            public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                dataId.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CharacterModel> call, Throwable t) {
                dataId.setValue(null);
            }

        });
        return dataId;
    }

    public ArrayList<CharacterModel> getCharacter() {
        return (ArrayList<CharacterModel>) App.characterDao.getAll();
    }

}