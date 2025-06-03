package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityCalListBinding;

import java.util.HashMap;
import java.util.Map;

public class BasicCalActivity extends BaseActivity {

    ActivityCalListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Diamond Calculator";
        super.onCreate(savedInstanceState);


        binding = ActivityCalListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        Map<View, Pair<Class<?>, String>> guideMap = new HashMap<>();
        guideMap.put(binding.btnBasicCalculator, new Pair<>(A27_DiamondCalActivity.class, "Basic Calculator"));
        guideMap.put(binding.btnNormalCalculator, new Pair<>(A27_DiamondCalActivity.class, "Normal Calculator"));
        guideMap.put(binding.btnAdvanceCalculator, new Pair<>(A27_DiamondCalActivity.class, "Advanced Calculator"));

        View.OnClickListener guideClickListener = v -> {
            Pair<Class<?>, String> targetPair = guideMap.get(v);
            if (targetPair != null) {
                Intent intent = new Intent(this, targetPair.first);
                intent.putExtra("CALCULATOR_NAME", targetPair.second);
                startActivity(intent);
            }
        };

        for (View view : guideMap.keySet()) {
            view.setOnClickListener(guideClickListener);
        }
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

}