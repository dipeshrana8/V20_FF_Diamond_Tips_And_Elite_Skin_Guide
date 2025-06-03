package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.myAds.WebNativeAds;
import com.fansquad.ffdiatips.skinrewards.myAds.WebNavigationUtils;

public class BaseActivity extends AppCompatActivity {

    protected boolean showBackButton = true;
    protected boolean showSettings = true;
    protected String toolbarHeaderText = "Get Daily Diamond";
    protected boolean showNativeAds = true;
    protected boolean showInterstitialAds = true;
    protected boolean backShowInterstitialAds = true;

    private FrameLayout nativeAdContainer;


    protected void setupToolbar(TextView headerTextView, @Nullable View backsBtn, @Nullable View settingsBtn) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
        }

        if (headerTextView != null) {
            headerTextView.setText(toolbarHeaderText);
            headerTextView.setSelected(true);
        }


        if (backsBtn != null) {
            backsBtn.setVisibility(showBackButton ? View.VISIBLE : View.INVISIBLE);

        }
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (showInterstitialAds) {
            WebNavigationUtils.WebInterstitial(this);
        }

        overridePendingTransition(0, 0);
    }


    protected void shareApp() {
        String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app: " + appUrl);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }


    protected void rateApp() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    protected void openPrivacyPolicy() {
        Intent intent = new Intent(this, A4_PrivacyActivity.class);
        startActivity(intent);
    }


    protected void myBackActivity() {

        if (backShowInterstitialAds) {
            WebNavigationUtils.WebInterstitial(this);
        }
        finish();
        overridePendingTransition(0, 0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadNativeAdsIfNeeded();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initAdContainer();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initAdContainer();
    }

    private void initAdContainer() {
        try {

            nativeAdContainer = findViewById(R.id.frameNative);
        } catch (Exception e) {
            nativeAdContainer = null;
        }
    }

    protected void loadNativeAdsIfNeeded() {
        if (showNativeAds && nativeAdContainer != null) {
            WebNativeAds.NativeClass(nativeAdContainer, this);
            nativeAdContainer.setVisibility(View.VISIBLE);
        } else if (nativeAdContainer != null) {
            nativeAdContainer.setVisibility(View.GONE);
        }
    }


}
