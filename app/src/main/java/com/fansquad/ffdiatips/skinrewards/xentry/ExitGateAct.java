package com.fansquad.ffdiatips.skinrewards.xentry;

import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.CloseframeAltendBinding;

public class ExitGateAct extends CoreHostFx {

    CloseframeAltendBinding closeframeAltendBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Confirm Exit";
        enableSettingsNav = true;
        super.onCreate(savedInstanceState);
        closeframeAltendBinding = CloseframeAltendBinding.inflate(getLayoutInflater());
        setContentView(closeframeAltendBinding.getRoot());
        initTitleBarUx(
                closeframeAltendBinding.toolbarLayout.headerTitle,
                closeframeAltendBinding.toolbarLayout.btnBack,
                closeframeAltendBinding.toolbarLayout.btnSettings
        );
        closeframeAltendBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());
        initTitleBarUx(closeframeAltendBinding.toolbarLayout.headerTitle, closeframeAltendBinding.toolbarLayout.btnBack, closeframeAltendBinding.toolbarLayout.btnSettings);

        closeframeAltendBinding.btnYes.setOnClickListener(v -> {
            finishAffinity();
        });


        closeframeAltendBinding.btnNo.setOnClickListener(v -> {

            exitWithBridgeAd();
        });

    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}