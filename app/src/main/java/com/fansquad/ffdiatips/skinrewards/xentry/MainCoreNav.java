package com.fansquad.ffdiatips.skinrewards.xentry;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.MainframeCorezoneBinding;

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
        mainframeCorezoneBinding.btnAllSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("Guide", false);
                editor.apply();
                Intent intent = new Intent(MainCoreNav.this, VisualWrapAct.class);
                startActivity(intent);

            }
        });

        mainframeCorezoneBinding.btnEmotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("Guide", false);
                editor.apply();
                Intent intent = new Intent(MainCoreNav.this, ActionPulseAct.class);
                startActivity(intent);

            }
        });
        mainframeCorezoneBinding.btnDiamondGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("Guide", true);
                editor.apply();
                Intent intent = new Intent(MainCoreNav.this, GameModeActivity.class);
                startActivity(intent);

            }
        });
        mainframeCorezoneBinding.headerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiamondClaimDialog();
            }
        });


        mainframeCorezoneBinding.btnGetDiamonAtmGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainCoreNav.this, ATMActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainCoreNav.this, ExitGateAct.class);
        startActivity(intent);


    }

    private void showDiamondClaimDialog() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        int totalReward = sharedPreferences.getInt("reward_value", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_diamond_claim, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txtTitle = dialogView.findViewById(R.id.titleText);
        TextView txtSubtitle = dialogView.findViewById(R.id.subTitleText);
        ProgressBar progressBar = dialogView.findViewById(R.id.progressBar);
        TextView txtDiamond = dialogView.findViewById(R.id.txtDiamondCount);
        Button btnAction = dialogView.findViewById(R.id.btnAction);

        progressBar.setProgress(totalReward);
        txtDiamond.setText(totalReward + " 💎");

        if (totalReward >= 5000) {
            btnAction.setText("Claim now");
            btnAction.setOnClickListener(v -> {
                opennot();
                dialog.dismiss();
            });
        } else {
            btnAction.setText("Okay");
            btnAction.setOnClickListener(v -> dialog.dismiss());
        }

        dialog.show();
    }

    private void opennot() {
        View dialogView = LayoutInflater.from(MainCoreNav.this).inflate(R.layout.modal_twentyfourcore1s, null);
        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(MainCoreNav.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogView.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                dialog.dismiss();
            }
        });

        dialog.show();
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