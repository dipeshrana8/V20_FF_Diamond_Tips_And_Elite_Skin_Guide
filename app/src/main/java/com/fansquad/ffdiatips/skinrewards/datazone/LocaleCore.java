package com.fansquad.ffdiatips.skinrewards.datazone;

public class LocaleCore {
    private final String languageName;
    private final String hello;
    private final int flagResId;
    private boolean isSelected;


    public LocaleCore(String languageName, String hello, int flagResId, boolean isSelected) {
        this.languageName = languageName;
        this.hello = hello;
        this.flagResId = flagResId;
        this.isSelected = isSelected;
    }

    public String getHello() {
        return hello;
    }

    public String getLanguageName() {
        return languageName;
    }

    public int getFlagResId() {
        return flagResId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

