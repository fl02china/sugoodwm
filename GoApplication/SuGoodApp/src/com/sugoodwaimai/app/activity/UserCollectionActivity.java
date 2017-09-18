package com.sugoodwaimai.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.adapter.CollectionPagerAdapter;
import com.sugoodwaimai.app.fragment.ProductCollectionFragment;
import com.sugoodwaimai.app.fragment.ProductCollectionFragment2;
import com.sugoodwaimai.app.fragment.ShopCollectionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Package :com.android.supermarket.user
 * Description :
 * Author :Rc3
 * Created at :2017/3/10 10:24.
 */

public class UserCollectionActivity extends AppCompatActivity {


    TabLayout mTabLayout;
    Context mContext;
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_collection_activity);
        ButterKnife.bind(this);
        mContext = this;
        Handler a=new Handler();
        a.sendEmptyMessage(1);
        initView();
        intiData();
    }

    @OnClick(R.id.user_collection_header_back)
    void onback() {
        finish();
    }

    private void intiData() {


        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new ShopCollectionFragment());
        list.add(new ProductCollectionFragment());
        //list.add(new ProductCollectionFragment());

        mViewPager.setAdapter(new CollectionPagerAdapter(getSupportFragmentManager(), list));


        mTabLayout.setupWithViewPager(mViewPager);


    }


    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.user_collection_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.user_collection_viewpager);
    }
}
