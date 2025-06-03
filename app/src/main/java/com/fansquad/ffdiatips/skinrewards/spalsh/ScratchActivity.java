package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityScratchBinding;
import com.fansquad.ffdiatips.skinrewards.myAds.WebNavigationUtils;

public class ScratchActivity extends BaseActivity {


    ActivityScratchBinding binding;
    private final int diamonds = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScratchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        updateDiamondCounter();

        binding.scratchCardView.setOnScratchListener(() -> {
            int reward = (int) (Math.random() * 100);
            if (reward > 20) {
                youWinnner();
            } else {
                tryNextTime();
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        int totalReward = sharedPreferences.getInt("reward_value", 0);


        binding.txtCountEarn.setText("Total : " + totalReward);
    }

    private void youWinnner() {


        View dialogView = LayoutInflater.from(ScratchActivity.this).inflate(R.layout.dialog_spin_collect, null);
        AlertDialog dialog = new AlertDialog.Builder(ScratchActivity.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogView.findViewById(R.id.btnCollect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                int currentTotal = sharedPreferences.getInt("reward_value", 0); // default is 0

                int my_new = Integer.parseInt("50");
                int updatedTotal = currentTotal + my_new;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("reward_value", updatedTotal);
                editor.apply();

                binding.txtCountEarn.setText("Total : " + updatedTotal);


                WebNavigationUtils.WebInterstitial(ScratchActivity.this);
                dialog.dismiss();
            }
        });


        dialogView.findViewById(R.id.txtHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebNavigationUtils.WebInterstitial(ScratchActivity.this);
                dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.txAD2X).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                int currentTotal = sharedPreferences.getInt("reward_value", 0); // default is 0

                int my_new = Integer.parseInt("100");
                int updatedTotal = currentTotal + my_new;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("reward_value", updatedTotal);
                editor.apply();

                binding.txtCountEarn.setText("Total : " + updatedTotal);
                WebNavigationUtils.WebInterstitial(ScratchActivity.this);
                dialog.dismiss();

            }
        });

        dialog.show();
    }

    private void tryNextTime() {

        View dialogView = LayoutInflater.from(ScratchActivity.this).inflate(R.layout.dialog_next_time, null);
        AlertDialog dialog = new AlertDialog.Builder(ScratchActivity.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogView.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                prefs.edit().putBoolean("spin_dialog_shown", true).apply();
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
    }

    private void updateDiamondCounter() {

        binding.txtCountEarn.setText("Total : " + diamonds);
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}