package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.os.Bundle;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivitySettingBinding;

import java.util.HashMap;
import java.util.Map;

public class A6_SettingActivity extends BaseActivity {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Setting";


        super.onCreate(savedInstanceState);


        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar(binding.toolbarLayout.headerTitle, binding.toolbarLayout.btnBack, binding.toolbarLayout.btnSettings);
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        Map<View, Runnable> actionMap = new HashMap<>();

        actionMap.put(binding.btnPrivacyPolicy, this::openPrivacyPolicy);
        actionMap.put(binding.btnRateApp, this::rateApp);
        actionMap.put(binding.btnShareApp, this::shareApp);

        View.OnClickListener smartClick = v -> {
            if (actionMap.containsKey(v)) {
                showInterstitialAds = false;
                actionMap.get(v).run();
            }
        };


        for (View view : actionMap.keySet()) {
            view.setOnClickListener(smartClick);
        }

    }

    @Override
    public void onBackPressed() {
        myBackActivity();


    }


}