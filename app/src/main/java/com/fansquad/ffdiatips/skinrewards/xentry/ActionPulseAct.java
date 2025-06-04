package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.DisplayGesturexMapBinding;

public class ActionPulseAct extends CoreHostFx {

    DisplayGesturexMapBinding displayGesturexMapBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Expression Icons";
        super.onCreate(savedInstanceState);


        displayGesturexMapBinding = DisplayGesturexMapBinding.inflate(getLayoutInflater());
        setContentView(displayGesturexMapBinding.getRoot());
        initTitleBarUx(
                displayGesturexMapBinding.toolbarLayout.headerTitle,
                displayGesturexMapBinding.toolbarLayout.btnBack,
                displayGesturexMapBinding.toolbarLayout.btnSettings
        );
        displayGesturexMapBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


        displayGesturexMapBinding.btnNormalEmotes.setOnClickListener(v -> {
            Intent intent = new Intent(ActionPulseAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Et Normal");
            startActivity(intent);
        });


        displayGesturexMapBinding.btnRareEmotes.setOnClickListener(v -> {
            Intent intent = new Intent(ActionPulseAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Et Rare");
            startActivity(intent);
        });

        displayGesturexMapBinding.btnSpecialEmotes.setOnClickListener(v -> {
            Intent intent = new Intent(ActionPulseAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Et Special");
            startActivity(intent);
        });


    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}