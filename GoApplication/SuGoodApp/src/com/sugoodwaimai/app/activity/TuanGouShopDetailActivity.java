package com.sugoodwaimai.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class TuanGouShopDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuangou_shop_detail_activity);

        SugoodApplication.getInstance().getShopCarProductList().clear();

    }
}
