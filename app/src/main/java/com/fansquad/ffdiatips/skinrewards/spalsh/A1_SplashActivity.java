package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivitySplashBinding;
import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;


public class A1_SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;
    private boolean IntroChecked = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showNativeAds = false;
        showInterstitialAds = true;
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("spin_dialog_shown", false).apply();

        sharedPreferences = getSharedPreferences("FfPref", MODE_PRIVATE);
        IntroChecked = sharedPreferences.getBoolean("IntroChecked", false);
        // Enable verbose logging for debugging (remove in production)
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);
        // Initialize with your OneSignal App ID
        OneSignal.initWithContext(this, "72eccb2f-79e8-41fb-89ee-59e636d73339");
        // Use this method to prompt for push notifications.
        // We recommend removing this method after testing and instead use In-App Messages to prompt for notification permission.
        OneSignal.getNotifications().requestPermission(false, Continue.none());


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (IntroChecked) {
                    startActivity(new Intent(A1_SplashActivity.this, HomeActivity.class));

                    finish();
                } else {
                    startActivity(new Intent(A1_SplashActivity.this, Intro1Activity.class));

                    finish();
                }
            }
        }, 3000);

    }
}