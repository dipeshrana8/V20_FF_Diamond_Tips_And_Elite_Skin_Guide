package com.fansquad.ffdiatips.skinrewards.xentry;

import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.ScreenUsernavguideBinding;

public class AppGuideActivity extends CoreHostFx {

    ScreenUsernavguideBinding screenUsernavguideBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        titleBarTextDyn = "User Handbook";


        screenUsernavguideBinding = ScreenUsernavguideBinding.inflate(getLayoutInflater());
        setContentView(screenUsernavguideBinding.getRoot());
        initTitleBarUx(
                screenUsernavguideBinding.toolbarLayout.headerTitle,
                screenUsernavguideBinding.toolbarLayout.btnBack,
                screenUsernavguideBinding.toolbarLayout.btnSettings
        );
        screenUsernavguideBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}