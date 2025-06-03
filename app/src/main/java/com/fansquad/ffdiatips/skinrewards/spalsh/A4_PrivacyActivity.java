package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityPrivacyBinding;

public class A4_PrivacyActivity extends BaseActivity {
    private static final String PRIVACY_POLICY_URL = "https://getdailydiamondguideprivacy.blogspot.com/2025/04/get-daily-diamond-guide-privacy-policy.html";
    private ActivityPrivacyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showNativeAds = false;
        toolbarHeaderText = "Privacy Policy";

        super.onCreate(savedInstanceState);

        binding = ActivityPrivacyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());


        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );


        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl(PRIVACY_POLICY_URL);


    }

    @Override
    public void onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        } else {
            myBackActivity();
        }
    }
}