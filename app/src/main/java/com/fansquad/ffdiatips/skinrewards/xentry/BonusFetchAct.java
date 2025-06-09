package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.DailyboostLogframeBinding;

public class BonusFetchAct extends CoreHostFx {

    DailyboostLogframeBinding dailyboostLogframeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyboostLogframeBinding = DailyboostLogframeBinding.inflate(getLayoutInflater());
        setContentView(dailyboostLogframeBinding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("reward_value", 100);
        editor.apply();

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