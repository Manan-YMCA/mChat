package com.manan.mchat.Utilities;

import android.app.Application;

public class CustomFontApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontOverride.setDefaultFont(this, "DEFAULT", "font/louis_george_cafe.ttf");
        FontOverride.setDefaultFont(this, "MONOSPACE", "font/louis_george_cafe_bold.ttf");
        FontOverride.setDefaultFont(this, "SERIF", "font/louis_george_cafe.ttf");
        FontOverride.setDefaultFont(this, "SANS_SERIF", "font/louis_george_cafe.ttf");
        FontOverride.setDefaultFont(this, "SANS_SERIF_BOLD", "font/louis_george_cafe.ttf");
    }
}