package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityAppGuideBinding;

public class AppGuideActivity extends BaseActivity {

    ActivityAppGuideBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        toolbarHeaderText = "App Guide";


        binding = ActivityAppGuideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}