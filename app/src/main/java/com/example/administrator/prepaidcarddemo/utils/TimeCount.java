package com.example.administrator.prepaidcarddemo.utils;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by asus on 2018/4/15.
 */

public class TimeCount extends CountDownTimer {

    private Button btn_count;

    public TimeCount(long millisInFuture, long countDownInterval, Button btn_count) {
        super(millisInFuture, countDownInterval);
        this.btn_count = btn_count;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        btn_count.setEnabled(false);
        btn_count.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
        btn_count.setEnabled(true);
        btn_count.setText("发送");

    }
}