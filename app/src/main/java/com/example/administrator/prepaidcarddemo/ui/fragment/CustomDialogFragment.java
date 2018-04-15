package com.example.administrator.prepaidcarddemo.ui.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.administrator.prepaidcarddemo.R;

/**
 * Created by asus on 2018/4/15.
 * https://github.com/Othershe/NiceDialog
 */

public class CustomDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.add_mode, container);
        return view;
    }
}
