package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.MainframeCorezoneBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.NetRouteHelper;

import java.util.HashMap;
import java.util.Map;

public class MainCoreNav extends CoreHostFx {

    MainframeCorezoneBinding mainframeCorezoneBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "FF Diamond Tips And Elite Skin Guide";
        enableBackNav = false;

        super.onCreate(savedInstanceState);


        mainframeCorezoneBinding = MainframeCorezoneBinding.inflate(getLayoutInflater());
        setContentView(mainframeCorezoneBinding.getRoot());


        Map<View, Class<?>> navMap = new HashMap<>();

        navMap.put(mainframeCorezoneBinding.btnGetDiamond, GetDailyDiamondActivity.class);
        navMap.put(mainframeCorezoneBinding.btnAllSkin, VisualWrapAct.class);
        navMap.put(mainframeCorezoneBinding.btnEmotes, ActionPulseAct.class);
        navMap.put(mainframeCorezoneBinding.btnCalculator, LiteCalcFrame.class);
        navMap.put(mainframeCorezoneBinding.btnSettings, AppPrefNode.class);


        Map<View, Runnable> actionMap = new HashMap<>();


        View.OnClickListener smartClick = v -> {
            if (navMap.containsKey(v)) {
                enableMidAdsTrigger = true;
                startActivity(new Intent(this, navMap.get(v)));
                enableMidAdsTrigger = false;
            } else if (actionMap.containsKey(v)) {
                enableMidAdsTrigger = false;
                actionMap.get(v).run();
            }
        };


        for (View view : navMap.keySet()) {
            view.setOnClickListener(smartClick);
        }
        for (View view : actionMap.keySet()) {
            view.setOnClickListener(smartClick);
        }

        mainframeCorezoneBinding.btnGetDiamonAtmGames.setOnClickListener(v -> NetRouteHelper.triggerSparkFlow(MainCoreNav.this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainCoreNav.this, ExitGateAct.class);
        startActivity(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("IntroChecked", true);
        editor.apply();


        int totalReward = sharedPreferences.getInt("reward_value", 0);

        mainframeCorezoneBinding.headerTitle.setText("" + totalReward);
    }
}