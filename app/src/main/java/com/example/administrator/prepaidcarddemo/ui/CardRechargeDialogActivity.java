package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.utils.TimeCount;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;

import java.util.Date;

public class CardRechargeDialogActivity extends AppCompatActivity {
    private TimeCount timeCount;
    private boolean isSuccessOrFail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String data = getIntent().getStringExtra("data");
        String card = getIntent().getStringExtra("card");
        String balance = getIntent().getStringExtra("balance");
        NiceDialog.init()
                .setLayoutId(R.layout.bank_pay_page)
                .setConvertListener(new ViewConvertListener() {


                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        holder.setText(R.id.tv_num, data);
                        TextView tv_bank_name = holder.getView(R.id.tv_bank_name);
                        TextView tv_phone_num = holder.getView(R.id.tv_phone_num);
                        EditText et_code = holder.getView(R.id.et_code);
                        holder.setOnClickListener(R.id.tv_back, v -> finish());
                        holder.setOnClickListener(R.id.btn_determine_payment, v -> {
                            if (TextUtils.isEmpty(et_code.getText().toString())) {
                                Toast.makeText(CardRechargeDialogActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                            } else {
                                App.getInstance().putString("num", data);
                                if (!isSuccessOrFail) {

                                    //充值记录存入数据库
                                    Log.i("ljn", "充值记录: " + "card:" + card + "balance" + balance + "money" + data + "time" + App.getInstance().getTime());
                                    Intent intent = new Intent(CardRechargeDialogActivity.this, ReChargeSuccessActivity.class);
                                    intent.putExtra("num", data);
                                    startActivity(intent);

                                } else {
                                    startActivity(new Intent(CardRechargeDialogActivity.this, RechargeFailActivity.class));
                                }
                                if (timeCount != null) {
                                    timeCount.cancel();
                                }
                            }
                        });
                        holder.setOnClickListener(R.id.btn_get, v -> {
                            Button btn = (Button) v;
                            TimeCount timeCount = new TimeCount(60000, 1000, btn);
                            timeCount.start();
                        });


                    }
                })
                .setDimAmount(0.3f)
                .setShowBottom(false)
                .setMargin(20)
                .setHeight(600)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timeCount != null) {
            timeCount.cancel();
        }
    }
}
