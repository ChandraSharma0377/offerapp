package com.example.dummy.activities;

import android.app.Application;

import com.example.dummy.utility.FontsOverride;


public class MyApplication extends Application {

    private String fontName1 = "helvetica_neue.ttf";
    private String fontName = "helvetica_neue_light.ttf";

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", fontName);
        FontsOverride.setDefaultFont(this, "MONOSPACE", fontName);
        FontsOverride.setDefaultFont(this, "SERIF", fontName);
        FontsOverride.setDefaultFont(this, "SANS_SERIF", fontName);
    }
}
