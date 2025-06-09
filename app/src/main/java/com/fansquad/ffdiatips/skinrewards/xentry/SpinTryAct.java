package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
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
import com.fansquad.ffdiatips.skinrewards.databinding.WinluckCoretrackBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.NetRouteHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpinTryAct extends CoreHostFx {

    private final List<Integer> integers = new ArrayList<>();
    private final List<Integer> integers1 = new ArrayList<>();
    private final int[] images = {
            R.drawable.drx_moziqon38, R.drawable.drx_moziqon38, R.drawable.drx_moziqon38,
            R.drawable.drx_moziqon38, R.drawable.drx_moziqon38, R.drawable.drx_moziqon38,
            R.drawable.drx_moziqon38, R.drawable.drx_moziqon38, R.drawable.drx_moziqon38
    };

    private WinluckCoretrackBinding winluckCoretrackBinding;
    private ImageView[] imageViews;
    private int[] shuffledimages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Fortune Spin";
        super.onCreate(savedInstanceState);
        winluckCoretrackBinding = WinluckCoretrackBinding.inflate(getLayoutInflater());
        setContentView(winluckCoretrackBinding.getRoot());

        winluckCoretrackBinding.btnBack.setOnClickListener(v -> exitWithBridgeAd());

        setupGame();

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        int totalReward = sharedPreferences.getInt("reward_value", 0);
        winluckCoretrackBinding.txtCountEarn.setText("Total : " + totalReward);
    }

    private void setupGame() {
        imageViews = new ImageView[]{
                winluckCoretrackBinding.btn1, winluckCoretrackBinding.btn2, winluckCoretrackBinding.btn3,
                winluckCoretrackBinding.btn4, winluckCoretrackBinding.btn5, winluckCoretrackBinding.btn6,
                winluckCoretrackBinding.btn7, winluckCoretrackBinding.btn8, winluckCoretrackBinding.btn9
        };

        shuffledimages = images.clone();
        List<Integer> imageList = new ArrayList<>();
        for (int img : shuffledimages) imageList.add(img);
        Collections.shuffle(imageList);
        for (int i = 0; i < shuffledimages.length; i++) {
            shuffledimages[i] = imageList.get(i);
        }

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageResource(R.drawable.drx_moziqon52); // default image
            imageViews[i].setEnabled(true);
            int finalI = i;
            imageViews[i].setOnClickListener(v -> onImageClicked(finalI));
        }

        integers.clear();
        integers1.clear();

        restoreClickedButtonIfNeeded(); // apply saved clicked image
    }

    private void onImageClicked(int index) {
        SharedPreferences prefs = getSharedPreferences("SpinDataPrefs", MODE_PRIVATE);
        long lastClickTime = prefs.getLong("clicked_time", 0);
        long currentTime = System.currentTimeMillis();

        if ((currentTime - lastClickTime) < 24 * 60 * 60 * 1000) {
            showCooldownDialog();
            return;
        }

        if (integers.contains(index) || integers.size() >= 1) return;

        int selectedImage = shuffledimages[index];
        imageViews[index].setImageResource(selectedImage);
        imageViews[index].setEnabled(false);

        integers.add(index);
        integers1.add(selectedImage);

        saveClickedButtonData(index, selectedImage, currentTime);

        prefs.edit().putLong("last_spin_time", currentTime).apply();
        new Handler().postDelayed(this::checkResult, 1000);
    }

    private void checkResult() {
        int first = integers1.get(0);
        boolean allMatch = integers1.stream().allMatch(i -> i == first);

        if (allMatch) {
            Toast.makeText(this, "🎉 You got 50 points!", Toast.LENGTH_SHORT).show();
            youWinnner();
        } else {
            Toast.makeText(this, "Try Next Time!", Toast.LENGTH_SHORT).show();
        }

    }

    private void showCooldownDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.aftreclickdialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        dialogView.findViewById(R.id.btnOkay).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void youWinnner() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.modal_spinfetch, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        dialogView.findViewById(R.id.btnCollect).setOnClickListener(v -> {
            updateReward(50);
            dialog.dismiss();
            finish();
        });

        dialogView.findViewById(R.id.txAD2X).setOnClickListener(v -> {
            updateReward(100);
            dialog.dismiss();
            finish();
        });

        dialogView.findViewById(R.id.txtHome).setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(this, MainCoreNav.class));
            finish();
        });

        dialog.show();
    }

    private void updateReward(int amount) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        int currentTotal = sharedPreferences.getInt("reward_value", 0);
        sharedPreferences.edit().putInt("reward_value", currentTotal + amount).apply();
        winluckCoretrackBinding.txtCountEarn.setText("Total : " + (currentTotal + amount));
        NetRouteHelper.triggerSparkFlow(this);
    }

    private void saveClickedButtonData(int index, int imageResId, long time) {
        SharedPreferences prefs = getSharedPreferences("SpinDataPrefs", MODE_PRIVATE);
        prefs.edit()
                .putInt("clicked_index", index)
                .putInt("clicked_image", imageResId)
                .putLong("clicked_time", time)
                .apply();
    }

    private void restoreClickedButtonIfNeeded() {
        SharedPreferences prefs = getSharedPreferences("SpinDataPrefs", MODE_PRIVATE);
        long lastClickTime = prefs.getLong("clicked_time", 0);
        long now = System.currentTimeMillis();

        if ((now - lastClickTime) < 24 * 60 * 60 * 1000) {
            int index = prefs.getInt("clicked_index", -1);
            int imgRes = prefs.getInt("clicked_image", -1);
            if (index >= 0 && imgRes != -1 && index < imageViews.length) {
                imageViews[index].setImageResource(imgRes);
                imageViews[index].setEnabled(false);
                integers.add(index);
                integers1.add(imgRes);
            }
        } else {
            prefs.edit().clear().apply(); // clear expired click
        }
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}