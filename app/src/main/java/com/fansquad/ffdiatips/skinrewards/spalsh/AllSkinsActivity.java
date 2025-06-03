package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityAllSkinBinding;

import java.util.HashMap;
import java.util.Map;

public class AllSkinsActivity extends BaseActivity {


    ActivityAllSkinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "All Skins";


        super.onCreate(savedInstanceState);
        binding = ActivityAllSkinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());
        Map<View, Class<?>> guideMap = new HashMap<>();
        guideMap.put(binding.btnCharacter, CharacterActivity.class);


        View.OnClickListener guideClickListener = v -> {
            Class<?> target = guideMap.get(v);
            if (target != null) {
                startActivity(new Intent(this, target));
            }
        };

        for (View view : guideMap.keySet()) {
            view.setOnClickListener(guideClickListener);
        }

        binding.btnPet.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Pet");
            startActivity(intent);
        });

        binding.btnBundle.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Bundel");
            startActivity(intent);
        });
        binding.btnWeapons.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, WeaponsActivity.class);
            startActivity(intent);
        });

        binding.btnParachute.setOnClickListener(v -> {
            Intent intent = new Intent(AllSkinsActivity.this, CharacterListActivity.class);
            intent.putExtra("category", "Parachute");
            startActivity(intent);
        });

        binding.btnAppGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllSkinsActivity.this, AppGuideActivity.class);

                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}