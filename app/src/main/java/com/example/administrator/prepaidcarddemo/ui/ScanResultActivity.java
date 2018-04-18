package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.widget.EditText;

import com.example.administrator.prepaidcarddemo.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ScanResultActivity extends BaseActivity {


    @OnClick(R.id.btn_determine)
    void onClick() {
        //正确 进入密码界面
        startActivity(new Intent(this, EditPswActivity.class));
    }

    @BindView(R.id.et_scan)
    EditText text;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_result;
    }

    @Override
    protected void initDataAndEvent() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        showOrHideBack(true);
        showOrHideRecord(false);
        text.setText(data);
    }
}
