package com.fansquad.ffdiatips.skinrewards.xentry;

import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityGameGuidesBinding;

public class GameGuidesActivity extends CoreHostFx {

    ActivityGameGuidesBinding activityGameGuidesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Diamond Guide";
        super.onCreate(savedInstanceState);


        activityGameGuidesBinding = ActivityGameGuidesBinding.inflate(getLayoutInflater());
        setContentView(activityGameGuidesBinding.getRoot());
        initTitleBarUx(
                activityGameGuidesBinding.toolbarLayout.headerTitle,
                activityGameGuidesBinding.toolbarLayout.btnBack,
                activityGameGuidesBinding.toolbarLayout.btnSettings
        );
        activityGameGuidesBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

}