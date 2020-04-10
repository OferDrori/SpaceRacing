package com.example.Space_Racing;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {

    private SharedPreferences prefs;

    public MySharedPreferences(Context context) {
        prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);

    }

    public int getInt(String key, int defaultValue) {

        return prefs.getInt(key, defaultValue);
    }
    public float getFlut(String key, float defaultValue)
    {
        return prefs.getFloat(key, defaultValue);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public void putFlut(String key, float value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key, value);
        editor.apply();
    }


    public void removeKey(String key) {
        prefs.edit().remove(key);
    }
}