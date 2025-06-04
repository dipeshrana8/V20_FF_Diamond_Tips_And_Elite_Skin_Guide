package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.DailyboostLogframeBinding;

public class BonusFetchAct extends CoreHostFx {

    DailyboostLogframeBinding dailyboostLogframeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyboostLogframeBinding = DailyboostLogframeBinding.inflate(getLayoutInflater());
        setContentView(dailyboostLogframeBinding.getRoot());


        dailyboostLogframeBinding.btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(BonusFetchAct.this, CharacterSelectActivity.class);
            startActivity(intent);
        });
        dailyboostLogframeBinding.btnNotNow.setOnClickListener(v -> {
            Intent intent = new Intent(BonusFetchAct.this, CharacterSelectActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}