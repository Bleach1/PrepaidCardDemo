package com.example.administrator.prepaidcarddemo.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ReChargeSuccessActivity extends BaseActivity {

    @BindView(R.id.tv_num)
    TextView tv_num;

    @BindView(R.id.tv_order_num)
    TextView tv_order_num;

    @BindView(R.id.tv_money)
    TextView tv_money;

    @OnClick(R.id.tv_btn_text)
    void click() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pay_success;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initDataAndEvent() {
        setTitleAndShow("充值成功");
        showOrHideBack(true);
        showOrHideRecord(false);
        String num = getIntent().getStringExtra("num");
        tv_money.setText("¥ "+num);
        tv_num.setText(App.getInstance().getString("card_num"));
    }
}
