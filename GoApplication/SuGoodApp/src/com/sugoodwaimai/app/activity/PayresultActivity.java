package com.sugoodwaimai.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.util.ToastUtil;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class PayresultActivity extends BaseActivity {
    String type;
    String tradeno;
    String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payresult);
        Intent intentfinish = new Intent("FinishActivity");
        sendBroadcast(intentfinish);//发送对应的广播
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         type = bundle.getString("type");
        price= bundle.getString("price");
         tradeno=bundle.getString("outTradeNo");
        Button orderButton = (Button) findViewById(R.id.order);
       TextView tx_price = (TextView) findViewById(R.id.price);
        tx_price.setText("成功支付 ¥"+price);
        //getresult(type,tradeno);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin) {
                    //Toast.makeText(mContext, "订单", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent();

                    Bundle bundle=new Bundle();
                    bundle.putInt("type",1);

                    intent2.putExtras(bundle);
                    //    intent.setClass(MineActivity.this, UserOrderActivity.class);
                    intent2.setClass(PayresultActivity.this, OrderManagerActivity.class);
                    startActivity(intent2);
                    finish();
                } else {
//                    Intent intent = new Intent();
//                    intent.setClass(MineActivity.this, LoginActivity.class);
//                    startActivity(intent);
                    ToastUtil.setToast(PayresultActivity.this, "请先登录");
                }


            }
        });



    }


}
