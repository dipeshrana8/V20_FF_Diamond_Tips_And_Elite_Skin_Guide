package com.fansquad.ffdiatips.skinrewards.xentry;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityAtmactivityBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.AdPulsePrefs;
import com.fansquad.ffdiatips.skinrewards.zstream.NetRouteHelper;

public class ATMActivity extends CoreHostFx {
    private ActivityAtmactivityBinding policyReadvaultBinding;

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


                NetRouteHelper.triggerSparkFlow(ATMActivity.this);
                finish();
                dialog.dismiss();

            }
        });


        dialogView.findViewById(R.id.txtHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.txAD2X).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                int currentTotal = sharedPreferences.getInt("reward_value", 0);

                int my_new = Integer.parseInt("100");
                int updatedTotal = currentTotal + my_new;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("reward_value", updatedTotal);
                editor.apply();
                finish();

                NetRouteHelper.triggerSparkFlow(ATMActivity.this);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}