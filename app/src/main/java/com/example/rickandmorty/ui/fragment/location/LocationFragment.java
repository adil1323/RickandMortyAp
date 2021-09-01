package com.example.rickandmorty.ui.fragment.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentCharacterBinding;
import com.example.rickandmorty.databinding.FragmentLocationBinding;
import com.example.rickandmorty.ui.adapters.LocationAdapter;
import com.example.rickandmorty.ui.fragment.character.CharacterViewModel;

public class LocationFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    LocationAdapter adapter = new LocationAdapter();
    private LocationViewModel locationViewModel;
    private FragmentLocationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        initRecyclerView();
        fetchApiLoc();
        return binding.getRoot();
    }

    private void initRecyclerView() {
        binding.rvLoc.setAdapter(adapter);

    }

    private void fetchApiLoc() {
        if (isNetworkConnection()) {
            locationViewModel.fetchLocation().observe(getViewLifecycleOwner(), locationModelRickAndMortyResponse ->
                    adapter.setListLoc(locationModelRickAndMortyResponse.getResults()));

        } else {
            adapter.setListLoc(locationViewModel.getLocation());
        }
    }

    public boolean isNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}