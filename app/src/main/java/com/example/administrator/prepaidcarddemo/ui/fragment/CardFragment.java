package com.example.administrator.prepaidcarddemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.prepaidcarddemo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by asus on 2018/4/15.
 */

public class CardFragment extends Fragment {

    private Unbinder mUnBinder;
    private int type;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt("key");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (type == 0) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.add_prepaid_card, container, false);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.prepaid_card, container, false);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    public static CardFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("key", type);
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
