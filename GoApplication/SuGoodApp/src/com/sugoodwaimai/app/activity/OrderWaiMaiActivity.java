package com.sugoodwaimai.app.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.adapter.TabPageAdapter;
import com.sugoodwaimai.app.fragment.OrderAllFragment;
import com.sugoodwaimai.app.fragment.OrderCancleFragment;
import com.sugoodwaimai.app.fragment.OrderIngFragment;
import com.sugoodwaimai.app.fragment.OrderPayFragment;
import com.sugoodwaimai.app.fragment.OrderPingJiaFragment;
import com.sugoodwaimai.app.helper.TabRadioHelper;
import com.viewpagerindicator.UnderlinePageIndicator;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class OrderWaiMaiActivity extends BaseActivity {
    private TextView back;

    private UnderlinePageIndicator indicator;

    private RadioGroup mTabGroup;
    private ViewPager mPager;
    private UnderlinePageIndicator mIndicator;

    private TabRadioHelper mTabHelper;
    private TabPageAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);

        mPager = (ViewPager) findViewById(R.id.pager);
        // mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.view_padding));
        mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);

        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTabGroup = (RadioGroup) findViewById(R.id.tab_group);

        indicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
        mTabHelper = new TabRadioHelper(mTabGroup);

        initTab();
    }

    private void initTab() {
        mTabHelper.setViewPager(mPager);

        mTabAdapter = new TabPageAdapter(getSupportFragmentManager(), this);


        mTabAdapter.addTabFragment("orderAll", OrderAllFragment.class, null);
        mTabAdapter.addTabFragment("ordering", OrderIngFragment.class, null);
        mTabAdapter.addTabFragment("orderWait", OrderPayFragment.class, null);
        mTabAdapter.addTabFragment("orderPinjia", OrderPingJiaFragment.class, null);
        mTabAdapter.addTabFragment("orderCancle", OrderCancleFragment.class, null);

//        mTabAdapter.addTabFragment("1", OrderAllFragment.class, null);
//        mTabAdapter.addTabFragment("2", OrderAllFragment.class, null);
//        mTabAdapter.addTabFragment("3", OrderAllFragment.class, null);
//        mTabAdapter.addTabFragment("4", OrderAllFragment.class, null);
//        mTabAdapter.addTabFragment("5", OrderAllFragment.class, null);
        //mTabAdapter.addTabFragment("app", AppMangerFragment.class, null);
        mPager.setAdapter(mTabAdapter);

        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(mTabHelper);
    }
}
