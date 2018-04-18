package com.example.administrator.prepaidcarddemo.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.prepaidcarddemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class RecordActivity extends BaseActivity {

    @BindView(R.id.rv_record)
    RecyclerView rv_record;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }

    private List<String> strings = new ArrayList<>();

    @Override
    protected void initDataAndEvent() {
        showOrHideBack(true);
        setTitleAndShow("交易记录");
        showOrHideRecord(false);
        for (int i = 0; i < 25; i++) {
            strings.add("");
        }

        rv_record.setLayoutManager(new LinearLayoutManager(this));
        rv_record.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        BaseQuickAdapter adapter=new BaseQuickAdapter<String, BaseViewHolder>(R.layout.transaction_record_item, strings) {


            @Override
            protected void convert(BaseViewHolder helper, String item) {

            }

        };
        adapter.setOnLoadMoreListener(() -> {

        }, rv_record);
        adapter.setEnableLoadMore(true);
        rv_record.setAdapter(adapter);
    }
}
