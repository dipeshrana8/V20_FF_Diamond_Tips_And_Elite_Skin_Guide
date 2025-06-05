package com.fansquad.ffdiatips.skinrewards.xentry;

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
            R.drawable.drx_moziqon36, R.drawable.drx_moziqon38, R.drawable.drx_moziqon36,
            R.drawable.drx_moziqon38, R.drawable.drx_moziqon36, R.drawable.drx_moziqon38,
            R.drawable.drx_moziqon38, R.drawable.drx_moziqon38, R.drawable.drx_moziqon36
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
        for (int img : shuffledimages) {
            imageList.add(img);
        }
        Collections.shuffle(imageList);
        for (int i = 0; i < shuffledimages.length; i++) {
            shuffledimages[i] = imageList.get(i);
        }

        for (int i = 0; i < imageViews.length; i++) {
            int index = i;
            imageViews[i].setImageResource(R.drawable.drx_moziqon52);
            imageViews[i].setOnClickListener(v -> onImageClicked(index));
        }

        integers.clear();
        integers1.clear();
    }

    private void onImageClicked(int index) {
        if (integers.contains(index) || integers.size() >= 3)
            return;

        imageViews[index].setImageResource(shuffledimages[index]);
        integers.add(index);
        integers1.add(shuffledimages[index]);

        if (integers.size() == 3) {
            new Handler().postDelayed(this::checkResult, 1000);
        }
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

        new Handler().postDelayed(this::resetGameState, 1000);
    }

    private void youWinnner() {


        View dialogView = LayoutInflater.from(SpinTryAct.this).inflate(R.layout.modal_spinfetch, null);
        AlertDialog dialog = new AlertDialog.Builder(SpinTryAct.this)
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

                winluckCoretrackBinding.txtCountEarn.setText("Total : " + updatedTotal);


                NetRouteHelper.triggerSparkFlow(SpinTryAct.this);
                dialog.dismiss();
            }
        });


        dialogView.findViewById(R.id.txtHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetRouteHelper.triggerSparkFlow(SpinTryAct.this);
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

                winluckCoretrackBinding.txtCountEarn.setText("Total : " + updatedTotal);
                NetRouteHelper.triggerSparkFlow(SpinTryAct.this);
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private void resetGameState() {
        for (int index : integers) {
            imageViews[index].setImageResource(R.drawable.drx_moziqon52);
        }
        integers.clear();
        integers1.clear();
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}