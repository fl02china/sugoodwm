package com.sugoodwaimai.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class OrderManagerActivity  extends BaseActivity {
    private TextView back;
    private TextView txWaimai;
    private TextView txTuangou;
    private TextView txShangcheng;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiaty_ordermanager);

        initView();
    }

    private void initData(){

    }
    private void initView(){


        back = (TextView) findViewById(R.id.back);
        txWaimai = (TextView) findViewById(R.id.tx_waimai);
        txTuangou = (TextView) findViewById(R.id.tx_tuangou);
        txShangcheng = (TextView) findViewById(R.id.tx_shangcheng);
        back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

        if (getIntent().getIntExtra("type",0)!=1){
            back.setVisibility(View.GONE);
        }
        if (getIntent().getStringExtra("data")!=null){
            tip(getIntent().getStringExtra("data"));
        }
        txWaimai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin) {
                    Intent intent = new Intent();
                    //    intent.setClass(MineActivity.this, UserOrderActivity.class);
                    intent.setClass(OrderManagerActivity.this, OrderWaiMaiActivity.class);
                    startActivity(intent);
                }else{
                  tip("请先登录");
                }
            }
        });
        txTuangou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin){
                Intent intent = new Intent();
                //    intent.setClass(MineActivity.this, UserOrderActivity.class);
                intent.setClass(OrderManagerActivity.this, OrderTuanGouActivity.class);
                startActivity(intent);}else{
                    tip("请先登录");
                }
              //  tip("暂未开放");

            }
        });
        txShangcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (SugoodApplication.isLogin) {
//                Intent intent = new Intent();
//                //    intent.setClass(MineActivity.this, UserOrderActivity.class);
//                intent.setClass(OrderManagerActivity.this, OrderShanChengActivity.class);
//                startActivity(intent);
//            }else{
//                tip("请先登录");
//            }
                tip("暂未开放");
            }
        });
}
}
