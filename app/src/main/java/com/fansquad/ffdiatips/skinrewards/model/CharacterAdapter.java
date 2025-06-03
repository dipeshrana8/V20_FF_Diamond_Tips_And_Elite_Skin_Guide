package com.fansquad.ffdiatips.skinrewards.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fansquad.ffdiatips.skinrewards.databinding.ItemCharacterBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private final ArrayList<Character> characterList;
    private final OnCharacterClickListener listener;

    public CharacterAdapter(ArrayList<Character> characterList, OnCharacterClickListener listener) {
        this.characterList = characterList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.bind(character);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public interface OnCharacterClickListener {
        void onCharacterClick(Character character);
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        private final ItemCharacterBinding binding;

        CharacterViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Character character) {
            try {
                InputStream is = itemView.getContext().getAssets().open(character.getImagePath());
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                binding.characterImage.setImageBitmap(bitmap);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.getRoot().setOnClickListener(v -> listener.onCharacterClick(character));
        }
    }
}