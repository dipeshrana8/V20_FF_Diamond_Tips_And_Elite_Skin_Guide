package com.fansquad.ffdiatips.skinrewards.datazone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fansquad.ffdiatips.skinrewards.databinding.TileHeroentryBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AvatarBinder extends RecyclerView.Adapter<AvatarBinder.CharacterViewHolder> {
    private final ArrayList<AvatarMeta> avatarMetas;
    private final OnCharacterClickListener onCharacterClickListener;

    public AvatarBinder(ArrayList<AvatarMeta> characterList, OnCharacterClickListener listener) {
        this.avatarMetas = characterList;
        this.onCharacterClickListener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TileHeroentryBinding binding = TileHeroentryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        AvatarMeta character = avatarMetas.get(position);
        holder.bind(character);
    }

    @Override
    public int getItemCount() {
        return avatarMetas.size();
    }

    public interface OnCharacterClickListener {
        void onCharacterClick(AvatarMeta character);
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        private final TileHeroentryBinding tileHeroentryBinding;

        CharacterViewHolder(TileHeroentryBinding binding) {
            super(binding.getRoot());
            this.tileHeroentryBinding = binding;
        }

        void bind(AvatarMeta character) {
            try {
                InputStream is = itemView.getContext().getAssets().open(character.getImagePath());
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                tileHeroentryBinding.characterImage.setImageBitmap(bitmap);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tileHeroentryBinding.getRoot().setOnClickListener(v -> onCharacterClickListener.onCharacterClick(character));
        }
    }
}