package com.fansquad.ffdiatips.skinrewards.zstream;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class AdPulsePrefs {

    public static final String KEY_NATIVE_TOGGLE = "native_show";
    public static final String KEY_INTERSTITIAL_TOGGLE = "interstial_onoff";
    public static final String KEY_REDIRECT_URL = "redirect_link";
    private static final String KEY_STRING_LIST = "string_list_pref";
    private static final String KEY_INTERSTITIAL_PATH = "interstitial";
    private static final String KEY_NATIVE_URL = "native_link";

    // New keys
    private static final String KEY_POLICY_URL = "privacy_policy_link";
    private static final String KEY_SIGNAL_APP_ID = "onesignal_id";

    private static SharedPreferences preference() {
        return ZControlUnit.get().getSharedPreferences("adController", Context.MODE_PRIVATE);
    }

    public static String getRedirectLink() {
        return preference().getString(KEY_REDIRECT_URL, "");
    }

    public static void setRedirectLink(String value) {
        preference().edit().putString(KEY_REDIRECT_URL, value).apply();
    }

    public static void storeInterstitialRoute(String value) {
        preference().edit().putString(KEY_INTERSTITIAL_PATH, value).apply();
    }

    public static String getappopen_redirect() {
        return preference().getString(KEY_INTERSTITIAL_PATH, "");
    }

    public static boolean isNativeEnabled() {
        return preference().getBoolean(KEY_NATIVE_TOGGLE, false);
    }

    public static void setNative_show(boolean value) {
        preference().edit().putBoolean(KEY_NATIVE_TOGGLE, value).apply();
    }

    public static void setnative_link(String value) {
        preference().edit().putString(KEY_NATIVE_URL, value).apply();
    }

    public static String getnative_redirect_link() {
        return preference().getString(KEY_NATIVE_URL, "");
    }

    public static boolean getIninterstialWeb() {
        return preference().getBoolean(KEY_INTERSTITIAL_TOGGLE, false);
    }

    public static void setIninterstialWeb(boolean value) {
        preference().edit().putBoolean(KEY_INTERSTITIAL_TOGGLE, value).apply();
    }

    public static ArrayList<String> getKeyStringList() {
        String json = preference().getString(KEY_STRING_LIST, null);
        if (json != null) {
            try {
                return new Gson().fromJson(json, new TypeToken<ArrayList<String>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public static void setKeyStringList(ArrayList<String> list) {
        String json = new Gson().toJson(list);
        preference().edit().putString(KEY_STRING_LIST, json).apply();
    }

    public static String getPrivacyPolicyUrl() {
        return preference().getString(KEY_POLICY_URL, "");
    }

    public static void setPrivacyPolicyUrl(String url) {
        preference().edit().putString(KEY_POLICY_URL, url).apply();
    }

    public static String getOneSignalAppId() {
        return preference().getString(KEY_SIGNAL_APP_ID, "");
    }

    // New methods for OneSignal App ID
    public static void setOneSignalAppId(String appId) {
        preference().edit().putString(KEY_SIGNAL_APP_ID, appId).apply();
    }
}

