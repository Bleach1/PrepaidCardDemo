package com.example.administrator.prepaidcarddemo;

import android.app.Application;

import com.vondear.rxtools.RxTool;

/**
 * Created by asus on 2018/4/13.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
    }
}
