package com.fansquad.ffdiatips.skinrewards.myAds;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.fansquad.ffdiatips.skinrewards.R;


public class WebNavigationUtils {

    public static void ActivityStarturl(Context context, Class<?>... clsArr) {
        if (context == null) {
            return;
        }

        if (clsArr == null || clsArr.length == 0) {
            return;
        }

        for (Class<?> cls : clsArr) {
            if (cls == null) {
                continue;
            }

            try {
                Intent intent = new Intent(context, cls);
                context.startActivity(intent);
            } catch (Exception e) {
            }
        }

        WebInterstitial(context);
    }


    public static void WebInterstitial(Context context) {
        if (context == null) return;
        Log.e("TAG", "WebInterstitial:getIninterstialWeb------ " + AdsPreference.getIninterstialWeb());
        if (!AdsPreference.getIninterstialWeb()) return;

        String redirectUrl = AdsPreference.getRedirectLink();
        if (redirectUrl == null || redirectUrl.isEmpty()) return;
        Log.e("TAG", "WebInterstitial: redirectUrl------" + redirectUrl);
        try {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

            CustomTabColorSchemeParams colorParams = new CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(ContextCompat.getColor(context, R.color.colorprimary))
                    .build();
            builder.setDefaultColorSchemeParams(colorParams);

            builder.setShowTitle(true);
            builder.setShareState(CustomTabsIntent.SHARE_STATE_ON);
            builder.setInstantAppsEnabled(true);

            CustomTabsIntent customTabsIntent = builder.build();

            if (isChromeInstalled(context)) {
                customTabsIntent.intent.setPackage("com.android.chrome");
            } else {
                Log.e("WebInterstitial", "Chrome is not installed");
                return;
            }

            Uri uri = Uri.parse(redirectUrl);
            customTabsIntent.launchUrl(context, uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static boolean isChromeInstalled(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo("com.android.chrome", 0);
            return ai.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}