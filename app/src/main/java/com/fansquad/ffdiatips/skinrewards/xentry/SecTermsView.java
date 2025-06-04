package com.fansquad.ffdiatips.skinrewards.xentry;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.fansquad.ffdiatips.skinrewards.databinding.PolicyReadvaultBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.AdPulsePrefs;

public class SecTermsView extends CoreHostFx {
    private PolicyReadvaultBinding policyReadvaultBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableNativeAdsFlow = false;
        titleBarTextDyn = "Data Use Notice";

        super.onCreate(savedInstanceState);

        policyReadvaultBinding = PolicyReadvaultBinding.inflate(getLayoutInflater());
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
        String privacyUrl = AdPulsePrefs.getPrivacyPolicyUrl();
        if (!privacyUrl.isEmpty()) {
            policyReadvaultBinding.webView.loadUrl(privacyUrl);
        } else {
            policyReadvaultBinding.webView.loadData("<h2>No Privacy Policy URL available</h2>", "text/html", "UTF-8");
        }


    }

    @Override
    public void onBackPressed() {
        if (policyReadvaultBinding.webView.canGoBack()) {
            policyReadvaultBinding.webView.goBack();
        } else {
            exitWithBridgeAd();
        }
    }
}