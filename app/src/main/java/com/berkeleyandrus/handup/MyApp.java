package com.berkeleyandrus.handup;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String fontName = "fonts/montserrat_regular.otf";
        FontsOverride.setDefaultFont(this, "DEFAULT", fontName);
        FontsOverride.setDefaultFont(this, "MONOSPACE", fontName);
        FontsOverride.setDefaultFont(this, "SERIF", fontName);
        FontsOverride.setDefaultFont(this, "SANS_SERIF", fontName);
    }
}
