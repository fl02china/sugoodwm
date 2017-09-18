package com.sugoodwaimai.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.entity.ShopCarProduct;
import com.sugoodwaimai.app.entity.TakeawayShopInfo;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import com.sugoodwaimai.app.application.SugoodApplication;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class TuanGouSubmitOrderActivity extends BaseActivity {


    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.shop_name)
    TextView shopName;
//    @BindView(R.id.collect)
//    TextView collect;
    @BindView(R.id.head)
    RelativeLayout head;
    @BindView(R.id.sdv_tuangou)
    SimpleDraweeView sdvTuangou;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_price2)
    TextView tvPrice2;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    TakeawayShopInfo.EleBean shop;
    String price;
    JSONObject json;
    private FinshReceiver mFinsh;
    Context mContext;
    List<ShopCarProduct> shopCarList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuangou_submit_order_activity);
        ButterKnife.bind(this);
        mContext = this;
        mFinsh = new  FinshReceiver();
        registerReceiver(mFinsh, new IntentFilter("FinishActivity"));

        initDate();

    }
    //广播接收事件
    private class FinshReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mFinsh);
        super.onDestroy();
    }
    private void initDate() {
        json = new JSONObject();
        shop = (TakeawayShopInfo.EleBean) getIntent().getSerializableExtra("shop");
        String endDate = getIntent().getStringExtra("endDate");
        String goodname = getIntent().getStringExtra("goodname");
        String picture = getIntent().getStringExtra("picture");
        tvTime.setText(endDate);
        tvTitle.setText(goodname);
        tvPhone.setText(shop.getTel());
        sdvTuangou.setImageURI(Constant.PHOTOBASEURL + picture);
        shopCarList = SugoodApplication.getInstance().getShopCarProductList();
          price = getIntent().getStringExtra("price");
        shopName.setText(shop.getShopName());
        tvPrice.setText(price);
        tvPrice2.setText(price);
        try {
            json.put("userId", SugoodApplication.user.getUserId());
            json.put("addrId", "");
            json.put("logistics", shop.getLogistics());
            json.put("shopId", shop.getShopId());
            json.put("orderNum", 1);
            json.put("newMoney",  "" );
            json.put("totalPrice", price);
            json.put("needPay", price);
            json.put("message", "");

            JSONArray array = new JSONArray();
            for (int i = 0; i < shopCarList.size(); i++) {
                JSONObject json = new JSONObject();
                json.put("productId", shopCarList.get(i).getProductId());
                json.put("num", shopCarList.get(i).getFoodAmount());
                json.put("price", shopCarList.get(i).getFoodPrice());
                array.put(json);
            }
            json.put("details", array);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @OnClick(R.id.back)
    void back() {
        finish();
    }

    @OnClick(R.id.btn_submit)
    void btnSubmit() {
        goPay();
    }
    private void goPay(){
        try {
         //   String type = getIntent().getStringExtra("type");
//            if (type.equals("shop")) {
//                json.put("type", "3");
//            } else if (type.equals("waimai")) {
//                json.put("type", "1");
//            } else {
//                json.put("type", "2");
//            }
            json.put("type", "2");//团购
            json.put("sign", "1");
          //  json.put("message",submitRemark.getText().toString());
            showLoading("");
            RequestParams params = new RequestParams();
            params.put("orderDetails", json.toString());
            params.put("sign", "1");
            Log.e("TAA" +
                    "", "onClick: " + json.toString().toString());


//HttpUtil.post(Constant.SUGOODALIPAY, params, new JsonHttpResponseHandler()

            HttpUtil.post((Constant.SUGOODALIPAY), params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.e("TAA", "onSuccess: " + response.toString());
                    closeLoading();
                    try {
                        if (response.getBoolean("success")) {
                            Log.e("TAG2222", "onSuccess: " + response.toString());
                            String orderid =response.getString("orderId");

                            JSONObject body = new JSONObject();

                            body.put("type",json.getString("type"));
                            body.put("orderId",orderid);
                            body.put("shopName", shop.getShopName());
                            Intent intent = new Intent();
                            intent.putExtra("shopname", shop.getShopName());
                            intent.putExtra("orderId", orderid);
                            intent.putExtra("orderDetails", json.toString());
                            intent.putExtra("Body", body.toString());
                            intent.putExtra("type", json.getString("type"));
                            intent.putExtra("price", price);
                            //   intent.putExtra("time", price.toString());
                            intent.setClass(mContext, PaySelectActivity.class);


                            startActivityForResult(intent, 6);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.e("TAA", "onFailure: " + responseString);
                    closeLoading();
                }
            });



        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
