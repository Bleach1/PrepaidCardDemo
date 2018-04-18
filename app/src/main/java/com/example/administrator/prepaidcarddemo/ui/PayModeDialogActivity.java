package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.adapter.MultipleItemQuickAdapter;
import com.example.administrator.prepaidcarddemo.bean.MultipleItem;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/4/15.
 */

public class PayModeDialogActivity extends AppCompatActivity {

    private BaseNiceDialog show;
    private List<MultipleItem> multipleItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String data = getIntent().getStringExtra("data");
        multipleItems.add(new MultipleItem(1));
        multipleItems.add(new MultipleItem(2));
        multipleItems.add(new MultipleItem(2));
        multipleItems.add(new MultipleItem(3));
        show = NiceDialog.init()
                .setLayoutId(R.layout.pay_mode_list)
                .setConvertListener(new ViewConvertListener() {

                    private RecyclerView view;

                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.tv_back, v -> finish());
                        view = holder.getView(R.id.rv_pay_mode);
                        view.setLayoutManager(new LinearLayoutManager(PayModeDialogActivity.this));
                        view.addItemDecoration(new DividerItemDecoration(PayModeDialogActivity.this, DividerItemDecoration.VERTICAL));
                        MultipleItemQuickAdapter multipleItemQuickAdapter = new MultipleItemQuickAdapter(multipleItems);
                        multipleItemQuickAdapter.setOnItemClickListener((adapter, view, position) -> {
                            if (position == 0 || position == multipleItems.size() - 1) {
                                return;
                            }
                            Intent intent = new Intent(PayModeDialogActivity.this, CardRechargeDialogActivity.class);
                            intent.putExtra("data", data);
                            startActivity(intent);


                        });
                        view.setAdapter(multipleItemQuickAdapter);

                    }
                })
                .setDimAmount(0.3f)
                .setShowBottom(false)
                .setMargin(20)
                .setHeight(400)
                .setOutCancel(false)
                //.setAnimStyle(R.style.DefaultAnimation)
                .show(getSupportFragmentManager());
    }
}
