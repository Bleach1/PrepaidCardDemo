package com.example.administrator.prepaidcarddemo.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.prepaidcarddemo.App;
import com.example.administrator.prepaidcarddemo.BaseDao;
import com.example.administrator.prepaidcarddemo.Config;
import com.example.administrator.prepaidcarddemo.R;
import com.example.administrator.prepaidcarddemo.bean.CardBean;
import com.example.administrator.prepaidcarddemo.ui.fragment.CardFragment;
import com.example.administrator.prepaidcarddemo.zxing.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

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
    private RealmResults<CardBean> resulted;


    @OnClick(R.id.btn_recharge)
    void recharge() {
        App.getInstance().putString("card_num", resulted.get(mPosition).getNum());
        App.getInstance().putInt("position", mPosition);
        Intent intent = new Intent(this, RechargeActivity.class);
        intent.putExtra("card",resulted.get(mPosition).getNum());
        intent.putExtra("balance",resulted.get(mPosition).getBalance());
        startActivity(intent);
        Log.i("ljn", "recharge: " + mPosition);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void initDataAndEvent() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<CardBean> result = realm.where(CardBean.class).findAll();
        Log.i("ljn", "initDataAndEvent: " + result.size());
        if (result.size() == 0) {
            BaseDao baseDao = new BaseDao(realm);
            for (int i = 0; i < 4; i++) {
                CardBean cardBean = new CardBean();
                cardBean.setBalance("123");
                cardBean.setNum("123456789");
                cardBean.setType(0);
                cardBean.setId(i + 1);
                baseDao.insert(cardBean);
            }

        }

        resulted = realm.where(CardBean.class).findAll();

        Log.i("ljn", "initDataAndEvent: " + resulted.size());

        itemSize = resulted.size() + 1;
        if (itemSize == 1) {
            btn_recharge.setVisibility(View.GONE);
        }
        App.getInstance().putInt("size", itemSize);
        for (int i = 0; i < itemSize; i++) {
            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
            imageView.setLayoutParams(layoutParams);
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
                    if (position == App.getInstance().getInt("position")) {
                        BaseDao baseDao = new BaseDao(realm);
                        CardBean cardBean = new CardBean();
                        cardBean.setId(position + 1);
                        cardBean.setNum(resulted.get(position).getNum());
                        if (!TextUtils.isEmpty(App.getInstance().getString("num").trim())) {
                            int i = Integer.parseInt(resulted.get(position).getBalance().trim()) +
                                    Integer.parseInt(App.getInstance().getString("num").trim());
                            cardBean.setBalance(i + "");
                            baseDao.insertOrUpdate(cardBean);
                        }
                    }
                    return CardFragment.newInstance(0, resulted.get(position).getNum(), resulted.get(position).getBalance());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeAll("num");
    }
}
