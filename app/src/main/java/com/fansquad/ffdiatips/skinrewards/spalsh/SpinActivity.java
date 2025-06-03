package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityA30SpinBinding;
import com.fansquad.ffdiatips.skinrewards.myAds.WebNavigationUtils;

import java.util.Random;

public class SpinActivity extends BaseActivity {

    ActivityA30SpinBinding binding;
    Random random;
    int degreeOld = 0;
    int degree = 0;

    String[] rewards = {"10", "20", "30", "40", "50", "60"}; // Match wheel slices

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Spin Wheel";
        super.onCreate(savedInstanceState);
        binding = ActivityA30SpinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("spin_dialog_shown", true).apply();
        random = new Random(); // 🔥 Add this line

        binding.btnBack.setOnClickListener(v -> myBackActivity());
        binding.btnSpin.setOnClickListener(v -> spinWheel());


        prefs.edit().putBoolean("spin_shown", true).apply();
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        int totalReward = sharedPreferences.getInt("reward_value", 0);


        binding.txtCountEarn.setText("Total : " + totalReward);
    }

    private void spinWheel() {
        degreeOld = degree % 360;
        degree = random.nextInt(3600) + 720; // Random angle (2-10 full spins)

        RotateAnimation rotate = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotate.setDuration(4000);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());

        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.btnSpin.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.btnSpin.setEnabled(true);
                int finalDegree = 360 - (degree % 360);
                int sectorAngle = 360 / rewards.length;
                int index = finalDegree / sectorAngle;
                String reward = "50";


                View dialogView = LayoutInflater.from(SpinActivity.this).inflate(R.layout.dialog_spin_collect, null);
                AlertDialog dialog = new AlertDialog.Builder(SpinActivity.this)
                        .setView(dialogView)
                        .create();

                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                TextView txtCollect = dialogView.findViewById(R.id.txtCollect);


                txtCollect.setText("You won: " + reward + " diamonds");
                dialogView.findViewById(R.id.btnCollect).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                        int currentTotal = sharedPreferences.getInt("reward_value", 0); // default is 0

                        int my_new = Integer.parseInt(reward);
                        int updatedTotal = currentTotal + my_new;

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("reward_value", updatedTotal);
                        editor.apply();

                        binding.txtCountEarn.setText("Total : " + updatedTotal);


                        WebNavigationUtils.WebInterstitial(SpinActivity.this);
                        dialog.dismiss();
                    }
                });


                dialogView.findViewById(R.id.txtHome).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebNavigationUtils.WebInterstitial(SpinActivity.this);
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
                        WebNavigationUtils.WebInterstitial(SpinActivity.this);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        binding.ivWheel.startAnimation(rotate);
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

}