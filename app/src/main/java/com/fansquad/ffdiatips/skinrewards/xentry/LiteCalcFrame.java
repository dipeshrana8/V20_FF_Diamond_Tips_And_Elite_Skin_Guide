package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.ViewSchedparserLogBinding;

import java.util.HashMap;
import java.util.Map;

public class LiteCalcFrame extends CoreHostFx {

    ViewSchedparserLogBinding viewSchedparserLogBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Gem Count Estimator";
        super.onCreate(savedInstanceState);


        viewSchedparserLogBinding = ViewSchedparserLogBinding.inflate(getLayoutInflater());
        setContentView(viewSchedparserLogBinding.getRoot());
        initTitleBarUx(
                viewSchedparserLogBinding.toolbarLayout.headerTitle,
                viewSchedparserLogBinding.toolbarLayout.btnBack,
                viewSchedparserLogBinding.toolbarLayout.btnSettings
        );
        viewSchedparserLogBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


        Map<View, Pair<Class<?>, String>> guideMap = new HashMap<>();
        guideMap.put(viewSchedparserLogBinding.btnBasicCalculator, new Pair<>(RewardEstimator.class, "Basic Calculator"));
        guideMap.put(viewSchedparserLogBinding.btnNormalCalculator, new Pair<>(RewardEstimator.class, "Normal Calculator"));
        guideMap.put(viewSchedparserLogBinding.btnAdvanceCalculator, new Pair<>(RewardEstimator.class, "Advanced Calculator"));

        View.OnClickListener guideClickListener = v -> {
            Pair<Class<?>, String> targetPair = guideMap.get(v);
            if (targetPair != null) {
                Intent intent = new Intent(this, targetPair.first);
                intent.putExtra("CALCULATOR_NAME", targetPair.second);
                startActivity(intent);
            }
        };

        for (View view : guideMap.keySet()) {
            view.setOnClickListener(guideClickListener);
        }
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

}