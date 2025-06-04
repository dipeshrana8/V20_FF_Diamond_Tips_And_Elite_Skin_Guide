package com.fansquad.ffdiatips.skinrewards.zstream;

import android.app.Application;

public class ZControlUnit extends Application {

    public static ZControlUnit zControlUnit;

    public static ZControlUnit get() {
        return zControlUnit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        zControlUnit = this;

    }
}
