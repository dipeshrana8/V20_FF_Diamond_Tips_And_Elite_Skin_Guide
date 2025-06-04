package com.fansquad.ffdiatips.skinrewards.xentry;

import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.HintsUipacketBinding;

public class DiamondGuideActivity extends CoreHostFx {
    HintsUipacketBinding hintsUipacketBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleBarTextDyn = "Gem Strategy";


        hintsUipacketBinding = HintsUipacketBinding.inflate(getLayoutInflater());
        setContentView(hintsUipacketBinding.getRoot());
        initTitleBarUx(
                hintsUipacketBinding.toolbarLayout.headerTitle,
                hintsUipacketBinding.toolbarLayout.btnBack,
                hintsUipacketBinding.toolbarLayout.btnSettings
        );
        hintsUipacketBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}