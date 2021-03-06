package com.example.administrator.prepaidcarddemo.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.prepaidcarddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by asus on 2018/4/15.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @BindView(R.id.tv_back)
    TextView tv_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_transaction_record)
    TextView tv_transaction_record;

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @OnClick(R.id.tv_back)
    void finishActivity() {
        finish();
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initDataAndEvent();
    }

    protected abstract int getLayoutId();

    protected abstract void initDataAndEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected void setTitleAndShow(String title) {
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(title);
    }


    protected void showOrHideBack(boolean b) {
        if (b) {
            tv_back.setVisibility(View.VISIBLE);
            iv_back.setVisibility(View.VISIBLE);
        } else {
            tv_back.setVisibility(View.GONE);
            iv_back.setVisibility(View.GONE);
        }
    }


    protected void showOrHideRecord(boolean b) {
        if (b) {
            tv_transaction_record.setVisibility(View.VISIBLE);
        } else {
            tv_transaction_record.setVisibility(View.GONE);
        }
    }
}
