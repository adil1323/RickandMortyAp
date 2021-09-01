package com.example.rickandmorty.ui.fragment.episode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentEpisodeBinding;
import com.example.rickandmorty.model.EpisodeModel;
import com.example.rickandmorty.model.RickAndMortyResponse;
import com.example.rickandmorty.ui.adapters.EpisodeAdapters;

public class EpisodeFragment extends BaseFragment {

    EpisodeAdapters adapters = new EpisodeAdapters();
    private EpisodeViewModel episodeViewModel;
    private FragmentEpisodeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        episodeViewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        initRecyclerView1();
        fetchApiEpi();
        return binding.getRoot();
    }

    private void initRecyclerView1() {
        binding.rvEpi.setAdapter(adapters);

    }

    private void fetchApiEpi() {
        if (isNetworkConnection()) {
            episodeViewModel.fetchEpisode().observe(getViewLifecycleOwner(), episode -> adapters.setListEpi(episode.getResults()));
        }else {
            adapters.setListEpi(episodeViewModel.getEpisode());
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