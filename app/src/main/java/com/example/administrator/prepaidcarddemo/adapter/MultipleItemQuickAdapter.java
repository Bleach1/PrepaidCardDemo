package com.example.administrator.prepaidcarddemo.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.bean.MultipleItem;

import java.util.List;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TYPE_ONE, R.layout.purse_balance_item);
        addItemType(MultipleItem.TYPE_TWO, R.layout.bank_card_item);
        addItemType(MultipleItem.TYPE_THREE, R.layout.add_bank_card_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

    }
}
