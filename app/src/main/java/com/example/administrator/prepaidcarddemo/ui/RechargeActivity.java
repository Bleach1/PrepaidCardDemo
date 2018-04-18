package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.widgets.VerificationCodeInput;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.rg_change_num)
    RadioGroup radioGroup;
    @BindView(R.id.et_edit_num)
    EditText et_edit_num;
    private String s;
    private String text;
    private BaseNiceDialog show;

    @OnClick(R.id.tv_btn_text)
    void determine() {
        s = et_edit_num.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            show = NiceDialog.init()
                    .setLayoutId(R.layout.pay)
                    .setConvertListener(new ViewConvertListener() {
                        @Override
                        public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                            holder.setOnClickListener(R.id.iv_finish, v -> {
                                show.dismiss();
                            });
                            holder.setText(R.id.tv_rmb, "¥ " + s);
                            VerificationCodeInput view = holder.getView(R.id.vc_password);
                            view.setOnCompleteListener(content -> {
                                App.getInstance().putString("num", s);
                                Intent intent = new Intent(RechargeActivity.this, ReChargeSuccessActivity.class);
                                startActivity(intent);
                            });


                            holder.setOnClickListener(R.id.tv_forget_psw, v -> {

                            });
                            holder.setOnClickListener(R.id.iv_arrow, v -> {
                                Intent intent = new Intent(RechargeActivity.this, PayModeDialogActivity.class);
                                intent.putExtra("data", s);
                                startActivity(intent);
                            });

                        }
                    })
                    .setDimAmount(0.3f)
                    .setShowBottom(false)
                    .setMargin(20)
                    .setHeight(400)
                    .setOutCancel(false)
                    //.setAnimStyle(R.style.DefaultAnimation)
                    .show(getSupportFragmentManager());

        } else {
            Toast.makeText(this, "请输入金额", Toast.LENGTH_LONG).show();
        }

    }


    @Override

    protected int getLayoutId() {
        return R.layout.activity_card_recharge;
    }

    @Override
    protected void initDataAndEvent() {
        setTitleAndShow("卡片充值");
        showOrHideBack(true);
        showOrHideRecord(false);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton viewById = group.findViewById(checkedId);
            text = viewById.getText().toString();
            et_edit_num.setText(text.substring(1));
        });


    }
}
