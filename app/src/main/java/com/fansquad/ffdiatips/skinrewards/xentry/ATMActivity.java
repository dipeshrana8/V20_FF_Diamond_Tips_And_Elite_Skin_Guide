package com.fansquad.ffdiatips.skinrewards.xentry;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityAtmactivityBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.AdPulsePrefs;
import com.fansquad.ffdiatips.skinrewards.zstream.NetRouteHelper;

public class ATMActivity extends CoreHostFx {
    private ActivityAtmactivityBinding policyReadvaultBinding;

    private Handler standbyHandler;
    private Runnable standbyRunnable;
    private boolean isUserEligible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableNativeAdsFlow = false;
        titleBarTextDyn = "Play Online Games";

        super.onCreate(savedInstanceState);

        policyReadvaultBinding = ActivityAtmactivityBinding.inflate(getLayoutInflater());
        setContentView(policyReadvaultBinding.getRoot());

        policyReadvaultBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());

        initTitleBarUx(
                policyReadvaultBinding.toolbarLayout.headerTitle,
                policyReadvaultBinding.toolbarLayout.btnBack,
                policyReadvaultBinding.toolbarLayout.btnSettings
        );

        WebSettings webSettings = policyReadvaultBinding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        policyReadvaultBinding.webView.setWebViewClient(new WebViewClient());

        String redirectLink = AdPulsePrefs.getRedirectLink();
        if (!redirectLink.isEmpty()) {
            policyReadvaultBinding.webView.loadUrl(redirectLink);
        } else {
            policyReadvaultBinding.webView.loadData("<h2>No Privacy Policy URL available</h2>", "text/html", "UTF-8");
        }

        // Start 5-second timer
        standbyHandler = new Handler();
        standbyRunnable = () -> isUserEligible = true;
        standbyHandler.postDelayed(standbyRunnable, 60000);
    }

    @Override
    public void onBackPressed() {
        if (policyReadvaultBinding.webView.canGoBack()) {
            policyReadvaultBinding.webView.goBack();
        } else {
            youWinnner();
        }
    }

    private void youWinnner() {
        View dialogView = LayoutInflater.from(ATMActivity.this).inflate(R.layout.modal_spinfetch, null);
        AlertDialog dialog = new AlertDialog.Builder(ATMActivity.this)
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        dialogView.findViewById(R.id.btnCollect).setOnClickListener(v -> {


            if (isUserEligible) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                int currentTotal = sharedPreferences.getInt("reward_value", 0);
                int updatedTotal = currentTotal + 50;

                sharedPreferences.edit().putInt("reward_value", updatedTotal).apply();

                NetRouteHelper.triggerSparkFlow(ATMActivity.this);
                dialog.dismiss();
                finish();
            } else {
                Toast.makeText(this, "Play game for few minutes if you want diamonf", Toast.LENGTH_SHORT).show();
            }

        });

        dialogView.findViewById(R.id.txtHome).setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(ATMActivity.this, MainCoreNav.class));
            finish();
        });

        dialogView.findViewById(R.id.txAD2X).setOnClickListener(v -> {
            if (isUserEligible) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                int currentTotal = sharedPreferences.getInt("reward_value", 0);
                int updatedTotal = currentTotal + 100;

                sharedPreferences.edit().putInt("reward_value", updatedTotal).apply();

                NetRouteHelper.triggerSparkFlow(ATMActivity.this);
                dialog.dismiss();
                finish();
            } else {
                Toast.makeText(this, "Play game for few minutes if you want diamonf", Toast.LENGTH_SHORT).show();
            }


        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (standbyHandler != null && standbyRunnable != null) {
            standbyHandler.removeCallbacks(standbyRunnable);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (standbyHandler != null) {
            standbyHandler.removeCallbacks(standbyRunnable);
        }
    }
}
