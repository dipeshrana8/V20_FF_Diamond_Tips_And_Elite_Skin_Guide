package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityWeaponsBinding;

public class WeaponsActivity extends BaseActivity {

    ActivityWeaponsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Weapons";
        showSettings = true;
        super.onCreate(savedInstanceState);


        binding = ActivityWeaponsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        binding.btnWeaponsGuns.setOnClickListener(v -> {
            Intent intent = new Intent(WeaponsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Guns");
            startActivity(intent);
        });


        binding.btnProjectiles.setOnClickListener(v -> {
            Intent intent = new Intent(WeaponsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Projectiles");
            startActivity(intent);
        });


    }


    @Override
    public void onBackPressed() {
        myBackActivity();
    }

}