package com.fansquad.ffdiatips.skinrewards.xentry;

import android.os.Bundle;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.PrefsBoosterPageBinding;

import java.util.HashMap;
import java.util.Map;

public class AppPrefNode extends CoreHostFx {

    PrefsBoosterPageBinding prefsBoosterPageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "App Controls";


        super.onCreate(savedInstanceState);


        prefsBoosterPageBinding = PrefsBoosterPageBinding.inflate(getLayoutInflater());
        setContentView(prefsBoosterPageBinding.getRoot());

        initTitleBarUx(prefsBoosterPageBinding.toolbarLayout.headerTitle, prefsBoosterPageBinding.toolbarLayout.btnBack, prefsBoosterPageBinding.toolbarLayout.btnSettings);
        prefsBoosterPageBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


        Map<View, Runnable> actionMap = new HashMap<>();

        actionMap.put(prefsBoosterPageBinding.btnPrivacyPolicy, this::launchLegalFrame);
        actionMap.put(prefsBoosterPageBinding.btnRateApp, this::triggerAppRateCall);
        actionMap.put(prefsBoosterPageBinding.btnShareApp, this::triggerAppShareFlow);

        View.OnClickListener smartClick = v -> {
            if (actionMap.containsKey(v)) {
                enableMidAdsTrigger = false;
                actionMap.get(v).run();
            }
        };


        for (View view : actionMap.keySet()) {
            view.setOnClickListener(smartClick);
        }

    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();


    }


}