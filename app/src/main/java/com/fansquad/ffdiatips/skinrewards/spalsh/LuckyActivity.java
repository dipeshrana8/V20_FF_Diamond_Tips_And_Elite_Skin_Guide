package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityLuckyWinBinding;
import com.fansquad.ffdiatips.skinrewards.myAds.WebNavigationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LuckyActivity extends BaseActivity {

    private final List<Integer> selectedIndexes = new ArrayList<>();
    private final List<Integer> shownDrawables = new ArrayList<>();
    private ActivityLuckyWinBinding binding;
    private ImageView[] buttons;
    private final int[] images = {
            R.drawable.img_cry, R.drawable.img_earn, R.drawable.img_cry,
            R.drawable.img_earn, R.drawable.img_cry, R.drawable.img_earn,
            R.drawable.img_earn, R.drawable.img_earn, R.drawable.img_cry
    };
    private int[] shuffledImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Lucky Win";
        super.onCreate(savedInstanceState);
        binding = ActivityLuckyWinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnBack.setOnClickListener(v -> myBackActivity());

        setupGame();

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        int totalReward = sharedPreferences.getInt("reward_value", 0);


        binding.txtCountEarn.setText("Total : " + totalReward);
    }

    private void setupGame() {
        buttons = new ImageView[]{
                binding.btn1, binding.btn2, binding.btn3,
                binding.btn4, binding.btn5, binding.btn6,
                binding.btn7, binding.btn8, binding.btn9
        };

        shuffledImages = images.clone();
        List<Integer> imageList = new ArrayList<>();
        for (int img : shuffledImages) {
            imageList.add(img);
        }
        Collections.shuffle(imageList);
        for (int i = 0; i < shuffledImages.length; i++) {
            shuffledImages[i] = imageList.get(i);
        }

        for (int i = 0; i < buttons.length; i++) {
            int index = i;
            buttons[i].setImageResource(R.drawable.img_lucky_box); // initial image
            buttons[i].setOnClickListener(v -> onImageClicked(index));
        }

        selectedIndexes.clear();
        shownDrawables.clear();
    }

    private void onImageClicked(int index) {
        if (selectedIndexes.contains(index) || selectedIndexes.size() >= 3)
            return;

        buttons[index].setImageResource(shuffledImages[index]); // reveal image
        selectedIndexes.add(index);
        shownDrawables.add(shuffledImages[index]);

        if (selectedIndexes.size() == 3) {
            new Handler().postDelayed(this::checkResult, 1000);
        }
    }

    private void checkResult() {
        int first = shownDrawables.get(0);
        boolean allMatch = shownDrawables.stream().allMatch(i -> i == first);

        if (allMatch) {
            Toast.makeText(this, "🎉 You got 50 points!", Toast.LENGTH_SHORT).show();


            youWinnner();
        } else {
            tryNextTime();
        }

        new Handler().postDelayed(this::resetGameState, 1000);
    }

    private void youWinnner() {


        View dialogView = LayoutInflater.from(LuckyActivity.this).inflate(R.layout.dialog_spin_collect, null);
        AlertDialog dialog = new AlertDialog.Builder(LuckyActivity.this)
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


                WebNavigationUtils.WebInterstitial(LuckyActivity.this);
                dialog.dismiss();
            }
        });


        dialogView.findViewById(R.id.txtHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebNavigationUtils.WebInterstitial(LuckyActivity.this);
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
                WebNavigationUtils.WebInterstitial(LuckyActivity.this);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void tryNextTime() {

        View dialogView = LayoutInflater.from(LuckyActivity.this).inflate(R.layout.dialog_next_time, null);
        AlertDialog dialog = new AlertDialog.Builder(LuckyActivity.this)
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

    private void resetGameState() {
        for (int index : selectedIndexes) {
            buttons[index].setImageResource(R.drawable.img_lucky_box); // hide again
        }
        selectedIndexes.clear();
        shownDrawables.clear();
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}