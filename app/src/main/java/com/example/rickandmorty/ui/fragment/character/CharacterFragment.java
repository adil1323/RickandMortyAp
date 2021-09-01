package com.example.rickandmorty.ui.fragment.character;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.databinding.FragmentCharacterBinding;
import com.example.rickandmorty.ui.adapters.CharacterAdapter;

public class CharacterFragment extends BaseFragment {

    CharacterAdapter adapter = new CharacterAdapter();
    private CharacterViewModel characterViewModel;
    private FragmentCharacterBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        initRecyclerView();
        fetchApi();
        return binding.getRoot();
    }

    private void initRecyclerView() {
        binding.rvTask.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            if (isNetworkConnection()) {
                Navigation.findNavController(CharacterFragment.this.requireActivity(), R.id.nav_host_fragment_activity_main)
                        .navigate(CharacterFragmentDirections.actionCharacterFragmentToDetailCharacterFragment().setPosition(position));
            } else {
                Toast.makeText(requireContext(), "not internet alish^_^", Toast.LENGTH_LONG).show();
            }
        });

    }
    
    private void fetchApi() {
        if (isNetworkConnection()) {
            characterViewModel.fetchCharacter().observe(getViewLifecycleOwner(), character -> {
                if (character != null) {
                    Log.e("tg", "onChanged: " + character);
                    adapter.setList(character.getResults());
                }
            });
        } else {
            adapter.setList(characterViewModel.getCharacter());
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