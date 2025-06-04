package com.fansquad.ffdiatips.skinrewards.xentry;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ScreenDigitrunprevBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.NetRouteHelper;

public class RewardEstimator extends CoreHostFx {

    ScreenDigitrunprevBinding screenDigitrunprevBinding;
    String calculatorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleBarTextDyn = "Gem Count Estimator";
        screenDigitrunprevBinding = ScreenDigitrunprevBinding.inflate(getLayoutInflater());
        setContentView(screenDigitrunprevBinding.getRoot());

        screenDigitrunprevBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());
        calculatorName = getIntent().getStringExtra("CALCULATOR_NAME");

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
            Toast.makeText(this, "Enter Value Please !", Toast.LENGTH_SHORT).show();
            return;
        }
        View dialogView = LayoutInflater.from(RewardEstimator.this).inflate(R.layout.popup_statuscore, null);
        AlertDialog dialog = new AlertDialog.Builder(RewardEstimator.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogView.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetRouteHelper.triggerSparkFlow(RewardEstimator.this);
                dialog.dismiss();
            }
        });

        TextView etCal = dialogView.findViewById(R.id.txtCollect);

        dialog.show();
        String input = screenDigitrunprevBinding.etCal.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double diamonds = Double.parseDouble(input);
            double result = diamonds * 35;


            etCal.setText(String.format("If you play in ML Rank you will\nget: " + result + " ML Diamonds"));
        } catch (NumberFormatException e) {
            etCal.setText("Invalid input");

        }

    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

}