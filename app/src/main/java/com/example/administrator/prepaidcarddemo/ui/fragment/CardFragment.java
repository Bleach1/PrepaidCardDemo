package com.example.administrator.prepaidcarddemo.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.ui.ManualInputActivity;
import com.example.administrator.prepaidcarddemo.utils.Tools;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by asus on 2018/4/15.
 */

public class CardFragment extends Fragment implements View.OnClickListener {

    private Unbinder mUnBinder;
    private int type;
    private String num;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            type = arguments.getInt("key");
            num = arguments.getString("num");
        }
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        if (type == 0) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.prepaid_card, container, false);
            TextView tv_num = view.findViewById(R.id.tv_num);
            TextView tv_no = view.findViewById(R.id.tv_no);
            if (!TextUtils.isEmpty(num)) {
                tv_num.setText("¥ " + num);
            }

        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.add_prepaid_card, container, false);
            view.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    public static CardFragment newInstance(int type, String num) {
        Bundle args = new Bundle();
        args.putInt("key", type);
        args.putString("num", num);
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CardFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("key", type);
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private BaseNiceDialog show;

    @Override
    public void onClick(View v) {
        assert getFragmentManager() != null;
        show = NiceDialog.init()
                .setLayoutId(R.layout.add_mode)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.tv_scan, v12 -> {

                            RxPermissions rxPermission = new RxPermissions(Objects.requireNonNull(getActivity()));
                            rxPermission
                                    .requestEach(Manifest.permission.CAMERA)
                                    .subscribe(permission -> {
                                        if (permission.granted) {
                                            // 用户已经同意该权限
                                            Tools.scan(getActivity());

                                        } else if (permission.shouldShowRequestPermissionRationale) {
                                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                                        } else {
                                            // 用户拒绝了该权限，并且选中『不再询问』

                                        }
                                    });

                            show.dismiss();
                        });

                        holder.setOnClickListener(R.id.tv_manual, v1 -> {
                            startActivity(new Intent(getActivity(), ManualInputActivity.class));
                            show.dismiss();

                        });
                    }
                })
                .setDimAmount(0.3f)
                .setShowBottom(false)
                //.setMargin(20)
                .setWidth(250)
                .setHeight(200)
                .setOutCancel(true)
                .setAnimStyle(R.style.DefaultAnimation)
                .show(getFragmentManager());
    }
}
