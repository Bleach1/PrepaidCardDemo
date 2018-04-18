package com.example.administrator.prepaidcarddemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.example.administrator.prepaidcarddemo.Config;
import com.example.administrator.prepaidcarddemo.zxing.CaptureActivity;


/**
 * Created by asus on 2018/4/13.
 */

public class Tools {

    public static void scan(Activity context) {
        Intent intent = new Intent(context, CaptureActivity.class);
        //是否显示相册按钮
        intent.putExtra(CaptureActivity.INTENT_KEY_PHOTO_FLAG, true);
        //识别声音
        intent.putExtra(CaptureActivity.INTENT_KEY_BEEP_FLAG, true);
        //识别震动
        intent.putExtra(CaptureActivity.INTENT_KEY_VIBRATE_FLAG, true);
        //扫码框的颜色
        intent.putExtra(CaptureActivity.INTENT_KEY_SCSNCOLOR, "#FFFF00");
        //扫码框上面的提示文案
        intent.putExtra(CaptureActivity.INTENT_KEY_HINTTEXT, "请将二维码放入框中....");
        context.startActivityForResult(intent, Config.REQUEST_CODE);
    }


    public static String addStarForNum(String phoneNum) {
        StringBuilder sb = null;
        if (!TextUtils.isEmpty(phoneNum) && phoneNum.length() > 6) {
            sb = new StringBuilder();
            for (int i = 0; i < phoneNum.length(); i++) {
                char c = phoneNum.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
        }
        assert sb != null;
        return sb.toString();
    }
}
