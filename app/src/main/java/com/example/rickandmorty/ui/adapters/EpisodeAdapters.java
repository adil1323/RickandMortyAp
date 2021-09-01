package com.example.rickandmorty.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.databinding.ItemEpisodeBinding;
import com.example.rickandmorty.model.EpisodeModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EpisodeAdapters extends RecyclerView.Adapter<EpisodeAdapters.ViewHolder> {

    ArrayList<EpisodeModel> list = new ArrayList<>();
    ItemEpisodeBinding binding;

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    public void setListEpi(ArrayList<EpisodeModel> episodeModel) {
        this.list = episodeModel;
        notifyDataSetChanged();
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
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

        public void OnBind(EpisodeModel episodeModel) {
            binding.episode.setText(episodeModel.getEpisode());
            binding.nameEpi.setText(episodeModel.getName());
        }
    }
}
