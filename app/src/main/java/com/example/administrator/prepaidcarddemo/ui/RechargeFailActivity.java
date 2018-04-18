package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;

import com.example.administrator.prepaidcarddemo.R;

import butterknife.OnClick;

public class RechargeFailActivity extends BaseActivity {

    @OnClick(R.id.btn_pay_again)
    void again() {
        Intent intent = new Intent(this, RechargeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pay_fail;
    }

    @Override
    protected void initDataAndEvent() {
        setTitleAndShow("充值失败");
        showOrHideBack(true);
        showOrHideRecord(false);
    }
}
