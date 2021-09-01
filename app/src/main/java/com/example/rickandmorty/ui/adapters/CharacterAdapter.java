package com.example.rickandmorty.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.databinding.ItemCharacterBinding;
import com.example.rickandmorty.model.CharacterModel;
import com.example.rickandmorty.onItemclick.OnItemClick;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    ArrayList<CharacterModel> list = new ArrayList<>();
    private OnItemClick onItemClick;

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void setList(ArrayList<CharacterModel> characterModels) {
        this.list = characterModels;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.onItemClick = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.OnBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemCharacterBinding binding;

        public ViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void OnBind(CharacterModel item) {
            binding.itemTitleCharacter.setText(item.getName());
            Glide.with(binding.itemCharter)
                    .load(item.getImage())
                    .into(binding.itemCharter);
            binding.getRoot().setOnClickListener(v -> {
                onItemClick.onItemClick(item.getId());
            });
        }
    }
}













