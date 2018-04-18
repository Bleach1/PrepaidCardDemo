package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.Config;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.ui.fragment.CardFragment;
import com.example.administrator.prepaidcarddemo.zxing.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_container)
    ViewPager vp_container;
    @BindView(R.id.btn_recharge)
    Button btn_recharge;
    private int itemSize;
    @BindView(R.id.ll_point_container)
    LinearLayout linearLayout;
    private List<ImageView> imageViews = new ArrayList<>();

    @OnClick(R.id.btn_recharge)
    void recharge() {
        App.getInstance().putInt("position", mPosition);
        Intent intent = new Intent(this, RechargeActivity.class);
        startActivity(intent);
        Log.i("ljn", "recharge: "+mPosition);
    }

    private int mPosition;

    @OnClick(R.id.tv_transaction_record)
    void transactionRecord() {
        startActivity(new Intent(this, RecordActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDataAndEvent() {

        itemSize = App.getInstance().getInt("key");
        if (itemSize == 0) {
            App.getInstance().putInt("key", 4);
            itemSize = 4;
        }
        for (int i = 0; i < itemSize; i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.selector);
            } else {
                imageView.setBackgroundResource(R.drawable.normal);
            }
            imageViews.add(imageView);
            linearLayout.addView(imageView);
        }
        vp_container.setCurrentItem(0);
        vp_container.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == itemSize - 1) {
                    return CardFragment.newInstance(1);
                } else {

                    if (position == App.getInstance().getInt("position") && position != 0) {
                        return CardFragment.newInstance(0, App.getInstance().getString("num"));
                    } else {
                        return CardFragment.newInstance(0);
                    }

                }
            }

            @Override
            public boolean isViewFromObject(View view, Object obj) {
                return view == ((Fragment) obj).getView();
            }

            @Override
            public int getCount() {
                return itemSize;
            }


        });

        vp_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                for (int i = 0; i < itemSize; i++) {
                    if (i == position % itemSize) {
                        imageViews.get(i).setBackgroundResource(R.drawable.selector);
                    } else {
                        imageViews.get(i).setBackgroundResource(R.drawable.normal);
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_container.setPageMargin(20);
        vp_container.setOffscreenPageLimit(itemSize);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.REQUEST_CODE) {
            if (data == null) {
                return;
            }
            switch (resultCode) {
                case CaptureActivity.RESULT_SUCCESS:
                    String resultSuccess = data.getStringExtra(CaptureActivity.INTENT_KEY_RESULT_SUCCESS);
                    Intent intent = new Intent(MainActivity.this, ScanResultActivity.class);
                    intent.putExtra("data", resultSuccess);
                    startActivity(intent);
                    Log.i("ljn", "onActivityResult: " + resultSuccess);
                    break;
                case CaptureActivity.RESULT_FAIL:
                    String resultError = data.getStringExtra(CaptureActivity.INTENT_KEY_RESULT_ERROR);
                    // showToast(resultError);
                    break;
                case CaptureActivity.RESULT_CANCLE:
                    // showToast("取消扫码");
                    break;
                default:
                    break;
            }
        }
    }

}
