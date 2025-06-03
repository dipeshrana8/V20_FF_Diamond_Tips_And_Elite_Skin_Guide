package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityHomeBinding;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends BaseActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "FFF Skin Guide";
        showBackButton = false;

        super.onCreate(savedInstanceState);


        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.btnBack.setOnClickListener(v -> myBackActivity());


        Map<View, Class<?>> navMap = new HashMap<>();

        navMap.put(binding.btnGetDiamond, GetDailyDiamondActivity.class);
        navMap.put(binding.btnAllSkin, AllSkinsActivity.class);
        navMap.put(binding.btnEmotes, EmotesActivity.class);
        navMap.put(binding.btnCalculator, BasicCalActivity.class);
        navMap.put(binding.btnSettings, A6_SettingActivity.class);
//        binding.btnGlooWall.setOnClickListener(v -> {
//            Intent intent = new Intent(HomeActivity.this, CharacterListActivity.class);
//            intent.putExtra("category", "Gloowall");
//            startActivity(intent);
//        });

        Map<View, Runnable> actionMap = new HashMap<>();


        View.OnClickListener smartClick = v -> {
            if (navMap.containsKey(v)) {
                showInterstitialAds = true;
                startActivity(new Intent(this, navMap.get(v)));
                showInterstitialAds = false;
            } else if (actionMap.containsKey(v)) {
                showInterstitialAds = false;
                actionMap.get(v).run();
            }
        };


        for (View view : navMap.keySet()) {
            view.setOnClickListener(smartClick);
        }
        for (View view : actionMap.keySet()) {
            view.setOnClickListener(smartClick);
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeActivity.this, A7_ExitActivity.class);
        startActivity(intent);


    }


}