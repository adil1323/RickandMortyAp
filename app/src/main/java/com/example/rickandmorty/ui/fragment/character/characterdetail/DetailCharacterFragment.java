package com.example.rickandmorty.ui.fragment.character.characterdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.databinding.FragmentDetailBinding;
import com.example.rickandmorty.ui.fragment.character.CharacterViewModel;

import org.jetbrains.annotations.NotNull;


public class DetailCharacterFragment extends Fragment {

    FragmentDetailBinding binding;
    private CharacterViewModel characterViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initsalize();
        setupRequest();
    }

    private void initsalize() {
        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
    }

    private void setupRequest() {
        characterViewModel.fetchId(DetailCharacterFragmentArgs.fromBundle(getArguments()).getPosition()).observe(getViewLifecycleOwner(), characterModel -> {
            Glide.with(binding.image).load(characterModel.getImage()).into(binding.image);
            binding.created.setText(characterModel.getCreated());
            binding.gender.setText(characterModel.getGender());
            binding.specie.setText(characterModel.getSpecies());
            binding.status.setText(characterModel.getStatus());
            binding.type.setText(characterModel.getType());
        });
    }
}