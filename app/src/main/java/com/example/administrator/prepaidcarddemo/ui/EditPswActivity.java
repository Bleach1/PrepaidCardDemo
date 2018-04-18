package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.widgets.VerificationCodeInput;

import butterknife.BindView;
import butterknife.OnClick;

public class EditPswActivity extends BaseActivity {

    @OnClick(R.id.btn_determine)
    void onClick() {
        //回到主界面  刷新
        if (isEdited) {
            startActivity(new Intent(EditPswActivity.this, MainActivity.class));
            App.getInstance().putInt("key", App.getInstance().getInt("key") + 1);
            finish();
        } else {
            Toast.makeText(EditPswActivity.this, "请输入", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEdited;
    @BindView(R.id.vci_psw)
    VerificationCodeInput vci_psw;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_password;
    }

    @Override
    protected void initDataAndEvent() {
        showOrHideRecord(false);
        showOrHideBack(true);
        setTitleAndShow("输入卡密码");
        vci_psw.setOnCompleteListener(content -> {
            //判断密码是否正确
            isEdited = true;
        });
    }
}
