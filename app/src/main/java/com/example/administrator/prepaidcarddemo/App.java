package com.example.administrator.prepaidcarddemo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by asus on 2018/4/13.
 */

public class App extends Application {

    private SharedPreferences data;
    private SharedPreferences.Editor edit;
    public static App instance;

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        data = getSharedPreferences("data", MODE_PRIVATE);
        edit = data.edit();
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public void putInt(String key, int value) {
        edit.putInt(key, value).apply();
    }

    public int getInt(String key) {
        return data.getInt(key, 0);
    }

    public void putString(String key, String value) {
        edit.putString(key, value).apply();
    }

    public String getString(String key) {
        return data.getString(key, "");
    }


}
