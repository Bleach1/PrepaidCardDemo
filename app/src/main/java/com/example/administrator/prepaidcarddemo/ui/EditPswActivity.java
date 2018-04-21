package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.BaseDao;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.bean.CardBean;
import com.example.administrator.prepaidcarddemo.widgets.VerificationCodeInput;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class EditPswActivity extends BaseActivity {

    private Realm realm;

    @OnClick(R.id.btn_determine)
    void onClick() {
        //回到主界面  刷新
        if (isEdited) {
            BaseDao baseDao = new BaseDao(realm);
            CardBean cardBean = new CardBean();
            cardBean.setType(1);
            cardBean.setNum(num);
            cardBean.setBalance("123");
            cardBean.setId(App.getInstance().getInt("size"));
            baseDao.insert(cardBean);


            startActivity(new Intent(EditPswActivity.this, MainActivity.class));
            App.getInstance().putInt("key", App.getInstance().getInt("key") + 1);
            App.getInstance().putString("card", num);
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

    private String num;

    @Override
    protected void initDataAndEvent() {
        realm = Realm.getDefaultInstance();
        showOrHideRecord(false);
        showOrHideBack(true);
        setTitleAndShow("输入卡密码");
        num = getIntent().getStringExtra("num");
        vci_psw.setOnCompleteListener(content -> {
            //判断密码是否正确
            isEdited = true;
        });
    }
}
