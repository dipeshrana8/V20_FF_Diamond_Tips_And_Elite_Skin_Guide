package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.RewardDfluxDailyBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.NetRouteHelper;

import java.util.HashMap;
import java.util.Map;

public class GetDailyDiamondActivity extends CoreHostFx {

    RewardDfluxDailyBinding rewardDfluxDailyBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "FF Diamond Tips And Elite Skin Guide";


        super.onCreate(savedInstanceState);


        rewardDfluxDailyBinding = RewardDfluxDailyBinding.inflate(getLayoutInflater());
        setContentView(rewardDfluxDailyBinding.getRoot());


        rewardDfluxDailyBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


        Map<View, Class<?>> navMap = new HashMap<>();

        navMap.put(rewardDfluxDailyBinding.btnReward, SpinTryAct.class);


        View.OnClickListener smartClick = v -> {
            if (navMap.containsKey(v)) {
                enableMidAdsTrigger = true;
                startActivity(new Intent(this, navMap.get(v)));
                enableMidAdsTrigger = false;
            }
        };


        for (View view : navMap.keySet()) {
            view.setOnClickListener(smartClick);
        }

        rewardDfluxDailyBinding.btnMath.setOnClickListener(v -> openQuiz("Maths Quiz"));
        rewardDfluxDailyBinding.btnGK.setOnClickListener(v -> openQuiz("General Knowledge"));
        rewardDfluxDailyBinding.btnColor.setOnClickListener(v -> openQuiz("Color Puzzle"));
        rewardDfluxDailyBinding.btnGetDiamonAtmGames.setOnClickListener(v -> NetRouteHelper.triggerSparkFlow(GetDailyDiamondActivity.this));

    }

    private void openQuiz(String category) {
        Intent intent = new Intent(this, MindPlayView.class);
        intent.putExtra("SectionType", category);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();


    }


}