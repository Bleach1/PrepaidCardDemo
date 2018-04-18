package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.prepaidcarddemo.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ManualInputActivity extends BaseActivity {


    private String num;

    @OnClick({R.id.btn_determine})
    void onClick() {
        num = editText.getText().toString();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "请输入", Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, EditPswActivity.class));
        }
    }

    @BindView(R.id.et_num)
    EditText editText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_card_num;
    }

    @Override
    protected void initDataAndEvent() {
        showOrHideRecord(false);
        showOrHideBack(true);

    }
}
