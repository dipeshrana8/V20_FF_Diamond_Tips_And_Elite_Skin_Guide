package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityPlayerIdBinding;

public class PlayerIdActivity extends CoreHostFx {

    ActivityPlayerIdBinding screenDigitrunprevBinding;
    String calculatorName;

    boolean aBoolean;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleBarTextDyn = "Player Id";
        screenDigitrunprevBinding = ActivityPlayerIdBinding.inflate(getLayoutInflater());
        setContentView(screenDigitrunprevBinding.getRoot());

        screenDigitrunprevBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());
        calculatorName = "Player Id";

        if (calculatorName != null) {

            titleBarTextDyn = calculatorName;
        } else {
            titleBarTextDyn = calculatorName;
        }


        enableSettingsNav = true;

        initTitleBarUx(
                screenDigitrunprevBinding.toolbarLayout.headerTitle,
                screenDigitrunprevBinding.toolbarLayout.btnBack,
                screenDigitrunprevBinding.toolbarLayout.btnSettings
        );


        setupListeners();

    }


    private void setupListeners() {
        screenDigitrunprevBinding.imgBtnCalculator.setOnClickListener(v -> calculateDiamonds(calculatorName));
    }

    private void calculateDiamonds(String calculatorName) {


        String input1 = screenDigitrunprevBinding.etCal.getText() != null ? screenDigitrunprevBinding.etCal.getText().toString().trim() : "";

        if (input1.isEmpty()) {
            Toast.makeText(this, "Enter ID Please !", Toast.LENGTH_SHORT).show();
            return;
        }


        String input = screenDigitrunprevBinding.etCal.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Enter A Valid ID", Toast.LENGTH_SHORT).show();
            return;
        }
        preferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        aBoolean = preferences.getBoolean("Guide", false);
        if (aBoolean) {
            startActivity(new Intent(PlayerIdActivity.this, GameGuidesActivity.class));

            finish();
        } else {
            startActivity(new Intent(PlayerIdActivity.this, CoreDetailsFx.class));

            finish();
        }


    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

}