package com.mybrowser.pure;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;

public class ThemeManager {
    
    private static final String PREFS_NAME = "theme_prefs";
    private static final String KEY_DARK_MODE = "dark_mode";
    
    private Context context;
    private SharedPreferences prefs;
    
    public ThemeManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    public void applyTheme() {
        if (isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    
    public boolean isDarkMode() {
        return prefs.getBoolean(KEY_DARK_MODE, false);
    }
    
    public void setDarkMode(boolean enabled) {
        prefs.edit().putBoolean(KEY_DARK_MODE, enabled).apply();
        applyTheme();
    }
    
    public void toggleTheme() {
        setDarkMode(!isDarkMode());
    }
}
