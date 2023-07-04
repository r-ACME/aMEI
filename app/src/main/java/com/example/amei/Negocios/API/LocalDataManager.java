package com.example.amei.Negocios.API;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalDataManager {

    private SharedPreferences sharedPreferences;

    public LocalDataManager(Context context, String pref_name) {
        sharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
    }

    public void saveString(String data, String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        removeAllPermissions(key);
        editor.putString(key, data);
        editor.apply();
    }

    public void removeAllPermissions(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public String getString(String key) {
        String data = "";
        data = sharedPreferences.getString(key, null);
        return data;
    }

    public boolean haveData(String key) {
        return getString(key) != null;
    }

}