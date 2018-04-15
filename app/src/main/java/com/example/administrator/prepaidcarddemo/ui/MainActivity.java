package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.prepaidcarddemo.Config;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.utils.TimeCount;
import com.google.zxing.client.android.CaptureActivity;

import static com.vondear.rxtools.view.RxToast.showToast;

/**
 * https://github.com/rubensousa/ViewPagerCards
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDataAndEvent() {
/* TimeCount timeCount = new TimeCount(60000, 1000, btn);
        timeCount.start();*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.REQUEST_CODE) {
            if (data == null) {
                return;
            }
            switch (resultCode) {
                case CaptureActivity.RESULT_SUCCESS:
                    String resultSuccess = data.getStringExtra(CaptureActivity.INTENT_KEY_RESULT_SUCCESS);
                    showToast(resultSuccess);
                    break;
                case CaptureActivity.RESULT_FAIL:
                    String resultError = data.getStringExtra(CaptureActivity.INTENT_KEY_RESULT_ERROR);
                    showToast(resultError);
                    break;
                case CaptureActivity.RESULT_CANCLE:
                    showToast("取消扫码");
                    break;
                default:
                    break;
            }
        }
    }

}
