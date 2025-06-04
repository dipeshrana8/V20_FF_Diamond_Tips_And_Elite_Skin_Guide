package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.fansquad.ffdiatips.skinrewards.databinding.BootloadStagepanelBinding;
import com.fansquad.ffdiatips.skinrewards.zstream.AdPulsePrefs;
import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;


public class XBootInit extends CoreHostFx {

    BootloadStagepanelBinding bootloadStagepanelBinding;
    private boolean aBoolean = false;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableNativeAdsFlow = false;
        enableMidAdsTrigger = true;
        super.onCreate(savedInstanceState);
        bootloadStagepanelBinding = BootloadStagepanelBinding.inflate(getLayoutInflater());


        setContentView(bootloadStagepanelBinding.getRoot());

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("spin_dialog_shown", false).apply();

        preferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        aBoolean = preferences.getBoolean("IntroChecked", false);
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);

        String oneSignalAppId = AdPulsePrefs.getOneSignalAppId();
        if (!oneSignalAppId.isEmpty()) {
            OneSignal.initWithContext(this, oneSignalAppId);
            OneSignal.getNotifications().requestPermission(false, Continue.none());
        }


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (aBoolean) {
                    startActivity(new Intent(XBootInit.this, MainCoreNav.class));

                    finish();
                } else {
                    startActivity(new Intent(XBootInit.this, LegalFluxFrame.class));

                    finish();
                }
            }
        }, 3000);

    }
}