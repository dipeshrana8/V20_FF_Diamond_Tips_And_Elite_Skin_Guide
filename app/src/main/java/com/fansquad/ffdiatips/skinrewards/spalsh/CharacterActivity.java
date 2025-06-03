package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityCharactersBinding;

public class CharacterActivity extends BaseActivity {

    ActivityCharactersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCharactersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        toolbarHeaderText = "Character";


        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        binding.btnCharacterMale.setOnClickListener(v -> {
            Intent intent = new Intent(CharacterActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Male");
            startActivity(intent);
        });
        binding.btnCharacterFemale.setOnClickListener(v -> {
            Intent intent = new Intent(CharacterActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Female");
            startActivity(intent);
        });


    }


    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}
